plugins {
    id("com.example.android.application")
    id("com.example.android.application.compose")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
}

android {
    val buildVersionName = "1.0"
    val buildVersionCode = 1

    defaultConfig {
        applicationId = "com.example"
        versionCode = buildVersionCode
        versionName = buildVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE*}"
        }
    }

    namespace = "com.example"
}

dependencies {
    implementation(projects.core.design)
    implementation(projects.feature.start)
    implementation(projects.feature.main)

    compileOnly(libs.androidx.compose.compiler)

    coreLibraryDesugaring(libs.android.tools.desugar)

    // Android libraries
    implementation(libs.androidx.core)
    implementation(libs.androidx.core.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.startup)
    implementation(libs.material)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.lifecycle.compose)

    implementation(libs.androidx.activity.compose)

    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.params)

    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.assertions.core)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Third Party
    implementation(libs.compose.destinations.animations.core)
}
