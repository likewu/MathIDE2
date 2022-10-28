val    versions by extra(mapOf(
    "minSdk" to "21",
    "targetSdk" to "30",
    "compileSdk" to "30",
    "buildTools" to "30.0.3",

    // Library
    "publishVersionName" to "2.0.0",
    "publishVersionCode" to "7",
))

/*var testobject by extra(object {
    val aa = "aaaa"
    val bb = 1111
})*/

    // Plugins
val    agp_version = "7.1.1"

    // Core
val    kotlin_version = "1.7.20"
val    core_version = "1.9.0"
val    activity_version = "1.3.0"
val    fragment_version = "1.3.3"

    // Google Play
val    playcore_version = "1.8.1"

    // UI
val    appcompat_version = "1.3.0"
val    materialdesign_version = "1.3.0"
val    constraintlayout_version = "2.0.4"
val    swiperefreshlayout_version = "1.2.0"
val    preferencescompat_version = "1.1.1"
val    materialdialogs_version = "3.3.0"
val    recyclerview_selection_version = "1.1.0"

    // AAC
val    lifecycle_version = "2.3.1"
val    navigation_version = "2.3.5"
val    room_version = "2.4.3"

    // Network
val    gson_version = "2.8.6"

    // Coroutines
val    coroutines_version = "1.5.0"

    // DI
val    hilt_version = "2.44"

    // Other
val    keyboardevent_version = "3.0.0"
val    rhino_version = "1.7.13"
val    zip4j_version = "2.7.0"
val    chardet_version = "69.1"

    // Tests
val    junit_version = "4.13.2"
val    junit_ext_version = "1.1.2"
val    test_runner_version = "1.3.0"
val    espresso_core_version = "3.3.0"
val    mockito_version = "3.10.0"

    // Lint
val    ktlint_version = "0.40.0"

val    plugin by extra(mapOf(
    "android_gradle_plugin" to "com.android.tools.build:gradle:$agp_version",
    "kotlin_gradle_plugin" to "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version",
    "hilt_gradle_plugin" to "com.google.dagger:hilt-android-gradle-plugin:$hilt_version",
    "safeargs_gradle_plugin" to "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version",
))

val    library by extra(mapOf(
    // Core
    "kotlin" to "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version",
    "core" to "androidx.core:core-ktx:$core_version",
    "activity" to "androidx.activity:activity-ktx:$activity_version",
    "fragment" to "androidx.fragment:fragment-ktx:$fragment_version",

    // Google Play
    "playcore" to "com.google.android.play:core-ktx:$playcore_version",

    // UI
    "appcompat" to "androidx.appcompat:appcompat:$appcompat_version",
    "materialdesign" to "com.google.android.material:material:$materialdesign_version",
    "constraintlayout" to "androidx.constraintlayout:constraintlayout:$constraintlayout_version",
    "swiperefreshlayout" to "androidx.swiperefreshlayout:swiperefreshlayout:$swiperefreshlayout_version",
    "preferencescompat" to "androidx.preference:preference-ktx:$preferencescompat_version",
    "materialdialogs_core" to "com.afollestad.material-dialogs:core:$materialdialogs_version",
    "materialdialogs_color" to "com.afollestad.material-dialogs:color:$materialdialogs_version",
    "recyclerview_selection" to "androidx.recyclerview:recyclerview-selection:$recyclerview_selection_version",

    // AAC
    "livedata" to "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version",
    "viewmodel" to "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version",
    "navigation_ui" to "androidx.navigation:navigation-ui-ktx:$navigation_version",
    "navigation_fragment" to "androidx.navigation:navigation-fragment-ktx:$navigation_version",
    "room" to "androidx.room:room-ktx:$room_version",
    "room_compiler" to "androidx.room:room-compiler:$room_version",

    // Network
    "gson" to "com.google.code.gson:gson:$gson_version",

    // Coroutines
    "coroutines_core" to "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version",
    "coroutines_android" to "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version",

    // DI
    "hilt" to "com.google.dagger:hilt-android:$hilt_version",
    "hilt_compiler" to "com.google.dagger:hilt-compiler:$hilt_version",

    // Lint
    "ktlint" to "com.pinterest:ktlint:$ktlint_version",

    // Other
    "keyboardevent" to "net.yslibrary.keyboardvisibilityevent:keyboardvisibilityevent:$keyboardevent_version",
    "rhino" to "org.mozilla:rhino:$rhino_version",
    "zip4j" to "net.lingala.zip4j:zip4j:$zip4j_version",
    "chardet" to "com.ibm.icu:icu4j:$chardet_version",
))

val    testLibrary by extra(mapOf(
            "junit" to "junit:junit:$junit_version",
            "mockito" to "org.mockito:mockito-core:$mockito_version",
    ))

val    androidTestLibrary by extra(mapOf(
            "junit_ext" to "androidx.test.ext:junit:$junit_ext_version",
            "test_runner" to "androidx.test:runner:$test_runner_version",
            "espresso_core" to "androidx.test.espresso:espresso-core:$espresso_core_version",
    ))