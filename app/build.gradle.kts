plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val versions: Map<String, String> by project.extra

android {
    compileSdkVersion = versions["compileSdk"].toString()
    buildToolsVersion = versions["buildTools"].toString()

    defaultConfig {
        applicationId "cn.leafcolor.mathide"

        minSdkVersion versions["minSdk"].toString()
        targetSdkVersion versions["targetSdk"].toString()

        versionCode 10008
        versionName "2022.10.20"

        testInstrumentationRunner 'androidx.test.runner.AndroidJUnitRunner'
    }
    flavorDimensions "store"
    productFlavors {
        //googlePlay { dimension "store" }
        fdroid { dimension "store" }
        //baidu { dimension "store" }
    }
    signingConfigs {
        beta {
            storeFile project.file('E:/www/app-pvtool-android/clips/leafcolor.keystore')
            keyAlias 'leafcolor_mathide'
            storePassword System.getenv('ANDROID_STORE_PASSWORD')
            keyPassword System.getenv('ANDROID_KEY_PASSWORD')
        }
        release {
            storeFile project.file('E:/www/app-pvtool-android/clips/leafcolor.keystore')
            keyAlias 'leafcolor_mathide'
            storePassword System.getenv('ANDROID_STORE_PASSWORD')
            keyPassword System.getenv('ANDROID_KEY_PASSWORD')
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            debuggable false
            signingConfig signingConfigs.release
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        /*beta {
            versionNameSuffix "-beta-$defaultConfig.versionCode"
            debuggable false
            minifyEnabled false
            signingConfig signingConfigs.beta
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }*/
        debug {
            minifyEnabled false
            debuggable true
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    sourceSets {
        main.java.srcDirs += 'src/main/kotlin'
        //baidu.java.srcDirs += 'src/baidu/kotlin'
        fdroid.java.srcDirs += 'src/fdroid/kotlin'
        //googlePlay.java.srcDirs += 'src/googlePlay/kotlin'
        test.java.srcDirs += 'src/test/kotlin'
        androidTest.java.srcDirs += 'src/androidTest/kotlin'
        //Tell Gradle where to put the compiled shared library
        //main.jniLibs.srcDir 'libs'
        //disable automatic ndk-build call
        //main.jni.srcDirs = []
    }
    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    // Core
    implementation library.kotlin
    implementation library.core
    implementation library.activity
    implementation library.fragment

    // Google Play
    //googlePlayImplementation library.playcore

    // UI
    implementation library.appcompat
    implementation library.materialdesign
    implementation library.materialdialogs_core

    // AAC
    implementation library.livedata
    implementation library.viewmodel

    // Coroutines
    implementation library.coroutines_core
    implementation library.coroutines_android

    // DI
    implementation library.hilt
    kapt library.hilt_compiler

    // Modules
    implementation project(':domain')
    implementation project(':data')

    implementation project(':filesystems:filesystem-base')

    // Features
    implementation project(':features:feature-editor')
    implementation project(':features:feature-explorer')
    implementation project(':features:feature-fonts')
    implementation project(':features:feature-settings')
    implementation project(':features:feature-themes')
    implementation project(':features:feature-ui')
    implementation project(':features:feature-utils')

    // Tests
    testImplementation testLibrary.junit

    androidTestImplementation androidTestLibrary.junit_ext
    androidTestImplementation androidTestLibrary.test_runner
    androidTestImplementation androidTestLibrary.espresso_core
    implementation "androidx.core:core-ktx:+"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
}