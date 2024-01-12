plugins {
    id("com.example.android.feature")
    id("com.example.android.library.compose")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("com.example.kotlin.parcelize")
    id("org.jetbrains.kotlinx.kover")
    id("app.cash.paparazzi")
}

android {
    namespace = "com.example.core.design"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE*}"
        }
    }
}

dependencies {
    // Android libraries
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Coil
    implementation(libs.coil.compose)

    // Testing

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.testparameterinjector)
    testRuntimeOnly(libs.junit.vintage)

    // Third Party
    implementation(libs.compose.destinations.animations.core)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.compose.ui.manifest)
    androidTestImplementation(libs.mockk.android)
}
