package com.example.core.testing.compose.extension

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeTestRule

@OptIn(ExperimentalTestApi::class)
fun ComposeTestRule.waitUntilExists(
    matcher: SemanticsMatcher,
    timeoutMillis: Long = 1000L,
) {
    return this.waitUntilExactlyOneExists(matcher, timeoutMillis)
}

fun ComposeTestRule.waitUntilNodeCountAtLeast(
    matcher: SemanticsMatcher,
    count: Int = 1,
    timeoutMillis: Long = 1000L,
) {
    this.waitUntil(timeoutMillis) {
        this.onAllNodes(matcher).fetchSemanticsNodes().size >= count
    }
}
