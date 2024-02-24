plugins {
    id("com.example.android.feature")
    id("com.example.android.library.compose")
    id("com.example.android.hilt")
    id("com.example.ktlint")
    id("com.example.detekt")
    id("com.example.kotlin.parcelize")
    id("app.cash.paparazzi")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.feature.start"

    defaultConfig {
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

ksp {
    arg("compose-destinations.mode", "destinations")
    arg("compose-destinations.moduleName", "start")
}

dependencies {
    implementation(projects.core.design)

    // Android libraries
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    coreLibraryDesugaring(libs.android.tools.desugar)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.compose)

    implementation(libs.androidx.activity.compose)
    implementation(libs.google.accompanist.pager)

    implementation(libs.coil.compose)

    // Testing
    testImplementation(projects.core.testing.screenshots)

    testImplementation(libs.testparameterinjector)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.params)
    testImplementation(libs.junit.kotlinextensions)

    testImplementation(platform(libs.kotest.bom))
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.runner.junit5)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)

    androidTestImplementation(projects.core.testing.compose)
    androidTestImplementation(projects.core.testing.ui)
    androidTestImplementation(projects.core.testing.mockwebserver)

    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)

    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockwebserver.pathdispatcher)
    androidTestImplementation(libs.okhttp.mockwebserver)

    testImplementation(libs.testparameterinjector)
    testRuntimeOnly(libs.junit.vintage)

    // Third Party
    implementation(libs.compose.destinations.animations.core)
    ksp(libs.compose.destinations.ksp)
}
