package com.example.core.testing.mockwebserver.extensions

import pl.droidsonroids.testing.mockwebserver.FixtureDispatcher

fun FixtureDispatcher.defaults() {
    setFallbackResponse("500_response")
}
