plugins {
    id("com.example.android.library")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.core.testing.mockwebserver"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.okhttp.mockwebserver)
    implementation(libs.mockwebserver.pathdispatcher)
}
