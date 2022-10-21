val ktlint by configurations.creating

//configurations {
//    ktlint
//}

val library: Map<String, String> by project.extra

dependencies {
    ktlint(library["ktlint"].toString())
    //ktlint("com.pinterest:ktlint:0.40.0")
    //println(ktlint)
    //println(library["ktlint"])
}

val ktlintCheck by tasks.creating(JavaExec::class) {
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
}

// tasks.getByPath(':app:preBuild').dependsOn ktlintFormat