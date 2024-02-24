package com.example.core.testing.compose.testing

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.core.testing.compose.extension.context
import com.example.core.testing.mockwebserver.rule.MockWebServerRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import pl.droidsonroids.testing.mockwebserver.FixtureDispatcher

@HiltAndroidTest
abstract class ComposeHiltTest : ComposeTestRuleProvider {

    @Suppress("LeakingThis")
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    override val composeTestRule = createComposeRule()

    @get:Rule(order = 2)
    val mockWebServerRule = MockWebServerRule(context)

    protected val fixtureDispatcher: FixtureDispatcher
        get() = mockWebServerRule.fixtureDispatcher

    @BeforeEach
    open fun setUp() {
        hiltRule.inject()
    }
}
