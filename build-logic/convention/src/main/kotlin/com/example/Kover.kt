package com.example

import kotlinx.kover.api.KoverMergedConfig
import org.gradle.api.Project

private val coverageExclusions = listOf(
    "*R.class",
    "*R\$*.class",
    "*Manifest*.*",
    "*BuildConfig*",
    "*Component*",
    "*\$Lambda$*",
    "*Companion*",
    "*Extensions*",
    "dagger.hilt.*",
    "hilt_aggregated_deps.*",
    "*_Factory",
    "*_Factory\$*",
    "*_*Factory",
    "*_*Factory\$*",
    "*.Hilt_*",
    "*_HiltModules*",
    "*_HiltModules\$*",
    "*_Provide*Factory*",
)

internal fun Project.configureKover() {
    extensions.configure(KoverMergedConfig::class.java) {
        enable()

        filters {
            classes {
                excludes += coverageExclusions
            }
        }

        htmlReport {
            onCheck.set(true)
        }
    }
}
