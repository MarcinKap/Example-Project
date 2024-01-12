plugins {
    id("com.example.android.library")
    id("com.example.android.library.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    alias(libs.plugins.kotlin.serialization)
    id("com.karumi.kotlin-snapshot")
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.data.product"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    api(projects.core.networking)

    implementation(libs.kotlinx.coroutines.core)

    coreLibraryDesugaring(libs.android.tools.desugar)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.core)

    // Testing
    testImplementation(projects.core.testing.endpoints)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.params)
    testImplementation(libs.junit.kotlinextensions)

    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.assertions.core)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.okhttp.mockwebserver)
}
