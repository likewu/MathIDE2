import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import java.util.Date

val kotlin_version: String by rootProject.extra
val agp_version: String by rootProject.extra

buildscript {
    apply(from = "${rootProject.projectDir}/gradle/versions.gradle.kts")
}

plugins {
    val kotlinVersion = "1.7.20"  //"1.6.21"
    val agp_version = "7.1.1"

    id("com.android.application") version agp_version
    id("org.jetbrains.kotlin.android") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("com.github.ben-manes.versions") version "0.43.0"
}

android {
    compileSdkVersion(30)
}

//extra["swaggerVersion"] = "2.9.2"
val aa by extra("ggg")

allprojects {
    group = "com.pvtool"
    version = "1.1.0"

    repositories {
        maven(url="https://maven.aliyun.com/repository/google")
        maven(url="https://maven.aliyun.com/repository/public")  //jcenter
        maven(url="https://maven.aliyun.com/repository/central")
        maven(url="https://maven.aliyun.com/repository/gradle-plugin")
    }

    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    apply(from = "${rootProject.projectDir}/gradle/ktlint.gradle.kts")
}

subprojects {
    val library: Map<String, String> by rootProject.extra

    apply {
        plugin("org.jetbrains.kotlin.android")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Jar> {
        enabled = true
    }

    /*tasks.withType(JavaCompile).configureEach {
        //options.fork = true
        options.jvmTarget = "17"
    }*/
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

//tasks.create<Delete>("clean") {
//tasks.register("clean", Delete::class) {
//tasks.register<Delete>("clean") {
//}
/*tasks.named<Delete>("clean") {
    delete(rootProject.buildDir)
}*/

tasks.register<Copy>("installGitHook") {
    from(File(rootProject.rootDir, "pre-commit"))
    into { File(rootProject.rootDir, ".git/hooks") }
    fileMode = 777
}

//tasks.getByPath(':app:preBuild').dependsOn(installGitHook)


// Log timings per task.
class TimingsListener: TaskExecutionListener, BuildListener {
    private var startTime: Long = 0
    private var timings: MutableList<Timing> = mutableListOf()

    @Override
    override fun beforeExecute(task: Task) {
        startTime = System.nanoTime()
    }

    @Override
    override fun afterExecute(task: Task, taskState: TaskState) {
        val ms = TimeUnit.MILLISECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
        val currentTime = Date();
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        val dateString = formatter.format(currentTime);
        timings.add(Timing(ms, dateString, task.path))
        //task.project.logger.warn "${task.path} took ${ms}ms"
    }

    @Override
    override fun buildFinished(result: BuildResult) {
        println("Task timings:")
        for (timing in timings) {
            if (timing.ms >= 50) {
                System.out.printf("%7sms  %s  %s\n", timing.ms, timing.dateString, timing.path)
            }
        }
    }

    //@Override
    //override fun buildStarted(gradle: Gradle) {}

    @Override
    override fun projectsEvaluated(gradle: Gradle) {}

    @Override
    override fun projectsLoaded(gradle: Gradle) {}

    @Override
    override fun settingsEvaluated(settings: Settings) {}

    data class Timing(val ms: Long, val dateString: String, val path: String)
}

gradle.addListener(TimingsListener())