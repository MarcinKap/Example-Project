package com.example

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension

@Suppress("EnumEntryName", "EnumNaming")
enum class FlavorDimension {
    environment,
}

@Suppress("EnumEntryName", "EnumNaming")
enum class Flavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
) {
    staging(FlavorDimension.environment),
}

fun configureFlavors(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.environment.name
        productFlavors {
            Flavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            this.applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
