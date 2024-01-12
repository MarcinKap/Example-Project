plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ktlint.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.kover.gradlePlugin)

    /* Hilt artifacts starting from 2.40.2 depends on JavaPoet 1.13 but AGP still uses JavaPoet 1.10.
       In the meantime, we need to enforce using JavaPoet 1.13.0 until AGP updates it as a dependency.
       More info see this issue -> https://github.com/google/dagger/issues/3068
     */
    implementation(libs.javapoet)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.example.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "com.example.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidFeature") {
            id = "com.example.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidHilt") {
            id = "com.example.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLibraryHilt") {
            id = "com.example.android.library.hilt"
            implementationClass = "AndroidLibraryHiltConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.example.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "com.example.android.library.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("kotlinLibrary") {
            id = "com.example.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }

        register("ktlint") {
            id = "com.example.ktlint"
            implementationClass = "KtlintConventionPlugin"
        }

        register("detekt") {
            id = "com.example.detekt"
            implementationClass = "DetektConventionPlugin"
        }

        register("kotlinParcelize") {
            id = "com.example.kotlin.parcelize"
            implementationClass = "KotlinParcelizeConventionPlugin"
        }

        register("kotlinKover") {
            id = "com.example.kotlin.kover"
            implementationClass = "KotlinKoverConventionPlugin"
        }
    }
}
