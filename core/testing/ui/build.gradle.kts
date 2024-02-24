plugins {
    id("com.example.android.library")
    id("com.example.android.library.compose")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "com.example.feature.core.testing.ui"

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
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.testing)
}
