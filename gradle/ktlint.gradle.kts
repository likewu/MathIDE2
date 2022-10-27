//val ktlint by configurations.creating

//configurations {
//    ktlint
//}

//val testobject by project.extra
val library: Map<String, String> by rootProject.extra

dependencies {
    //ktlint(library["ktlint"].toString())
    //ktlint("com.pinterest:ktlint:0.40.0")
    //println(ktlint)
    //println(library["ktlint"])
    //println(testobject.bb)
}

/*val ktlintCheck by tasks.creating(JavaExec::class) {
    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
}*/

// tasks.getByPath(':app:preBuild').dependsOn ktlintFormat