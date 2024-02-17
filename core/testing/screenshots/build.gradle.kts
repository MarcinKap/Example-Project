plugins {
    id("com.example.android.library")
    id("com.example.android.library.compose")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.feature.core.testing.screenshots"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    compileOnly(libs.paparazzi)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)

    testImplementation(libs.testparameterinjector)
}
