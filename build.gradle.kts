import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

buildscript {
    apply(from = "${rootProject.projectDir}/gradle/versions.gradle.kts")
}

plugins {
    val kotlinVersion = "1.7.20"
    base
    kotlin("jvm") version kotlinVersion apply false
    /*plugin.android_gradle_plugin
    plugin.kotlin_gradle_plugin
    plugin.hilt_gradle_plugin
    plugin.safeargs_gradle_plugin*/
}

//extra["swaggerVersion"] = "2.9.2"
val aa by extra("ggg")

allprojects {
    group = "com.pvtool"
    version = "1.1.0"

    apply(from = "${rootProject.projectDir}/gradle/ktlint.gradle.kts")
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.kapt")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Jar> {
        enabled = true
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    dependencies {
        "api"(kotlin("stdlib-jdk8"))
        "api"(kotlin("reflect"))
        "api"("org.jetbrains.kotlin:kotlin-reflect")
        "api"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }
}

//tasks.create<Delete>("clean") {
//tasks.register("clean", Delete::class) {
//tasks.register<Delete>("clean") {
//}
tasks.named<Delete>("clean") {
    delete(rootProject.buildDir)
}

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
        val currentTime = java.util.Date();
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