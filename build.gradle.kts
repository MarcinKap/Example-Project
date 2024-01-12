buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.google.services.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.gradlePlugin)
        classpath(libs.ksp.gradlePlugin)
        classpath(libs.karumi.kotlinsnapshotPlugin)
        classpath(libs.fladle.gradlePlugin)
        classpath(libs.paparazzi.gradlePlugin)
    }
}

plugins {
    id("com.example.kotlin.kover")
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}
