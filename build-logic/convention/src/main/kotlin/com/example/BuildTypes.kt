package com.example

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun configureBuildTypes(baseAppModuleExtension: BaseAppModuleExtension) {
    with(baseAppModuleExtension) {
        buildTypes {
            getByName("debug") {
                isDebuggable = true
                isMinifyEnabled = false
                isShrinkResources = false
            }

            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            }
        }
    }
}
