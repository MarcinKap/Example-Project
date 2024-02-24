package com.example.core.testing.compose.testing

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class ComposeTest : ComposeTestRuleProvider {
    @get:Rule(order = 1)
    override val composeTestRule = createComposeRule()
}

