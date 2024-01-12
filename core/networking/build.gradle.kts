import com.example.Flavor

plugins {
    id("com.example.android.library")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    alias(libs.plugins.kotlin.serialization)
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.core.networking"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    productFlavors {
        getByName(Flavor.staging.name).apply {
            buildConfigField(
                type = "String",
                name = "BASE_API_URL",
                value = "\"https://dummyjson.com/\"",
            )
        }
    }
}

val stagingDebugImplementation by configurations.creating
val stagingReleaseImplementation by configurations.creating

dependencies {
    implementation(libs.hilt.testing)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(projects.core.testing.mockwebserver)

    stagingReleaseImplementation(libs.chucker.debug)
    stagingDebugImplementation(libs.chucker.debug)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.params)
    testImplementation(libs.junit.kotlinextensions)
    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.okhttp.mockwebserver)
    androidTestImplementation(libs.hilt.testing)
}
