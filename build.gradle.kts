buildscript {
    val kotlinVersion = "1.3.72"

    extra.set("kotlinVersion", kotlinVersion)

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.0.0")
    }

}

allprojects {

    repositories {
        mavenLocal()
        google()
        jcenter()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/aimybox/aimybox-android-sdk/")
    }
}
