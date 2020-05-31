val componentsVersion: String by rootProject.extra
val aimyboxVersion: String by rootProject.extra

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

android {

    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.sumit.assistant"

        minSdkVersion(23)
        targetSdkVersion(29)

        versionName = componentsVersion
        versionCode = 1
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            isTestCoverageEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
        }
    }

    lintOptions {
        isCheckAllWarnings = true
        isWarningsAsErrors = false
        isAbortOnError = true
    }
}

repositories {
    mavenLocal()
    google()
    jcenter()
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://dl.bintray.com/aimybox/aimybox-android-sdk/")
    maven("https://dl.bintray.com/aimybox/aimybox-android-assistant/")
}

dependencies {

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.3")

    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

    // Encrypted SharedPref
    implementation("androidx.security:security-crypto:1.0.0-rc02")

    // Aimybox
    implementation("com.justai.aimybox:components:$componentsVersion")
    implementation("com.justai.aimybox:core:$aimyboxVersion")
    implementation("com.justai.aimybox:google-platform-speechkit:$aimyboxVersion")
    implementation("com.justai.aimybox:rasa-api:$aimyboxVersion")

    // Lottie
    val lottieVersion = "3.4.0"
    implementation("com.airbnb.android:lottie:$lottieVersion")

    // Koin DI
    val koinVersion = "2.1.5"

    // Koin for Android (Kotlin)
    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.koin:koin-androidx-scope:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")
    implementation("org.koin:koin-android-ext:$koinVersion")
}
