package com.example.feature.start.startscreen.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.core.testing.compose.extension.waitUntilExists
import com.example.core.testing.compose.testing.ComposeTestRuleProvider
import com.example.feature.start.startscreen.composable.GET_STARTED_BUTTON_TEST_TAG

context(ComposeTestRuleProvider)
fun start(func: StartRobot.() -> Unit): StartRobot {
    composeTestRule.waitUntilExists(hasTestTag(GET_STARTED_BUTTON_TEST_TAG))
    return StartRobot(composeTestRule).apply(func)
}

class StartRobot(private val composeTestRule: ComposeTestRule) {
    private val getStatedButton
        get() = composeTestRule.onNodeWithTag(GET_STARTED_BUTTON_TEST_TAG)

    fun goToMainScreen() {
        getStatedButton
            .assertIsDisplayed()
            .performClick()
        composeTestRule.waitForIdle()
    }
}