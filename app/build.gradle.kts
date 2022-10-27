plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    //id("dagger.hilt.android.plugin")
}

val versions: Map<String, String> by rootProject.extra
val library: Map<String, String> by rootProject.extra
val testLibrary: Map<String, String> by rootProject.extra
val androidTestLibrary: Map<String, String> by rootProject.extra
val kotlin_version = "1.7.20"//: String by rootProject.extra

android {
    compileSdkVersion(30/*versions["compileSdk"].toString()*/)
    buildToolsVersion = versions["buildTools"].toString()

    defaultConfig {
        applicationId = "cn.leafcolor.mathide"

        minSdkVersion(versions["minSdk"].toString())
        targetSdkVersion(versions["targetSdk"].toString())

        versionCode = 10008
        versionName = "2022.10.20"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    flavorDimensions("store")
    productFlavors {
        //googlePlay { dimension = "store" }
        //fdroid { dimension = "store" }
        //baidu { dimension = "store" }
    }
    signingConfigs {
        create("release") {
            storeFile = project.file("E:/www/app-pvtool-android/clips/leafcolor.keystore")
            keyAlias = "leafcolor_mathide"
            storePassword = System.getenv("ANDROID_STORE_PASSWORD")
            keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
        }
        /*create("beta") {
            storeFile = project.file("E:/www/app-pvtool-android/clips/leafcolor.keystore")
            keyAlias = "leafcolor_mathide"
            storePassword = System.getenv("ANDROID_STORE_PASSWORD")
            keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
        }*/
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            isDebuggable  = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        /*named("beta").configure {
            versionNameSuffix "-beta-$defaultConfig.versionCode"
            debuggable false
            minifyEnabled false
            signingConfig signingConfigs.beta
            proguardFiles getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        }*/
        named("debug").configure {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    sourceSets {
        /*named("main") {
            java.srcDirs(listOf("src/main/kotlin"))
        }*/
        //baidu.java.srcDirs += "src/baidu/kotlin"
        //fdroid.java.srcDirs += "src/fdroid/kotlin"
        //googlePlay.java.srcDirs += "src/googlePlay/kotlin"
        /*named("test") {
            java.srcDirs("src/test/kotlin")
        }
        named("androidTest") {
            java.srcDirs("src/androidTest/kotlin")
        }*/
        //Tell Gradle where to put the compiled shared library
        //main.jniLibs.srcDir "libs"
        //disable automatic ndk-build call
        //main.jni.srcDirs = []
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree("libs") {
        include("*.jar")
    })

    // Core
    implementation(library["kotlin"].toString())
    implementation(library["core"].toString())
    implementation(library["activity"].toString())
    implementation(library["fragment"].toString())

    // Google Play
    //googlePlayImplementation library["playcore"].toString()

    // UI
    implementation(library["appcompat"].toString())
    implementation(library["materialdesign"].toString())
    implementation(library["materialdialogs_core"].toString())

    // AAC
    implementation(library["livedata"].toString())
    implementation(library["viewmodel"].toString())

    // Coroutines
    implementation(library["coroutines_core"].toString())
    implementation(library["coroutines_android"].toString())

    implementation("androidx.hilt:hilt-common:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    // DI
    //implementation("com.google.dagger:hilt-android-compiler:2.44")
    //implementation(library["hilt"].toString())
    //kapt("com.google.dagger:hilt-android-compiler:2.44")
    //kapt(library["hilt_compiler"].toString())
    //implementation("com.google.dagger:hilt-android:2.44")
    //kapt("com.google.dagger:hilt-compiler:2.44")
    //implementation(project(":test-processor"))
    //ksp(project(":test-processor"))

    implementation("com.google.dagger:hilt-android:2.44")
    annotationProcessor("com.google.dagger:hilt-compiler:2.44")
    //implementation(library["hilt_gradle_plugin"].toString())

    // Modules
    /*implementation(project(":domain"))
    implementation(project(":data"))

    implementation(project(":filesystems:filesystem-base"))

    // Features
    implementation(project(":features:feature-editor"))
    implementation(project(":features:feature-explorer"))
    implementation(project(":features:feature-fonts"))
    implementation(project(":features:feature-settings"))
    implementation(project(":features:feature-themes"))
    implementation(project(":features:feature-ui"))
    implementation(project(":features:feature-utils"))*/

    // Tests
    testImplementation(testLibrary["junit"].toString())

    androidTestImplementation(androidTestLibrary["junit_ext"].toString())
    androidTestImplementation(androidTestLibrary["test_runner"].toString())
    androidTestImplementation(androidTestLibrary["espresso_core"].toString())
    implementation("androidx.core:core-ktx:+")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
}