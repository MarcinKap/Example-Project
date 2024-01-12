package com.example

import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate

fun DependencyHandlerDelegate.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandlerDelegate.debugImplementation(dependencyNotation: Any) {
    add("debugImplementation", dependencyNotation)
}

fun DependencyHandlerDelegate.api(dependencyNotation: Any) {
    add("api", dependencyNotation)
}

fun DependencyHandlerDelegate.testImplementation(dependencyNotation: Any) {
    add("testImplementation", dependencyNotation)
}

fun DependencyHandlerDelegate.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}
