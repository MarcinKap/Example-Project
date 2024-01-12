package com.example.core.networking.deserializer

import kotlinx.serialization.json.Json

inline fun <reified T> String?.deserialize(): T? = this?.takeIf { it.isNotBlank() }?.let { Json.decodeFromString(it) }
