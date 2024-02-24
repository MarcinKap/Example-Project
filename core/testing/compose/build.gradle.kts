plugins {
    id("com.example.android.library")
    id("com.example.android.library.compose")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("org.jetbrains.kotlinx.kover")
    id("com.example.kotlin.parcelize")
}

android {
    namespace = "com.example.feature.core.testing.compose"

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
    implementation(projects.core.testing.mockwebserver)

    implementation(libs.androidx.test.ext.junit)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.test)

    implementation(libs.hilt.testing)

    implementation(libs.okhttp.mockwebserver)
    implementation(libs.mockwebserver.pathdispatcher)

    implementation(platform(libs.junit.bom))
    implementation(libs.junit.jupiter)

    implementation(libs.compose.destinations.animations.core)
}
