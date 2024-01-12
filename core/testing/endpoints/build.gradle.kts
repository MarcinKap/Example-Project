plugins {
    id("com.example.android.library")
    id("com.example.ktlint")
    id("com.example.detekt")
    alias(libs.plugins.kotlin.serialization)
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.core.testing.endpoints"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE*}"
        }
    }
}

dependencies {
    implementation(projects.core.networking)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(platform(libs.junit.bom))
    implementation(libs.junit.jupiter)
    implementation(libs.junit.params)
    implementation(libs.junit.kotlinextensions)

    implementation(platform(libs.kotest.bom))
    implementation(libs.kotest.assertions.core)

    implementation(libs.mockk)
    implementation(libs.kotlinx.coroutines.test)

    implementation(libs.okhttp.mockwebserver)
}
