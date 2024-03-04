package com.example.feature.start.startscreen.test

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.core.testing.compose.testing.ComposeHiltTest
import com.example.core.testing.ui.test.HiltTestActivity
import com.example.feature.start.startscreen.StartNavigationEvent
import com.example.feature.start.startscreen.StartNavigator
import com.example.feature.start.startscreen.StartViewModel
import com.example.feature.start.startscreen.composable.StartScreen
import com.example.feature.start.startscreen.robot.start
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class StartScreenTest : ComposeHiltTest() {

    override val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private val testNavigator: StartNavigator = mockk(relaxed = true)
    private val navigationEvent = MutableStateFlow<StartNavigationEvent>(StartNavigationEvent.Idle)
    private val viewModel: StartViewModel = mockk(relaxUnitFun = true) {
        every { navigationEventFlow } returns navigationEvent
    }

    @Before
    override fun setUp() {
        super.setUp()
        givenStartScreen()
    }

    @Test
    fun shouldNotifyViewModel_whenClickOnGetStarted() {
        start {
            goToMainScreen()

            verify { viewModel.onGetStarted() }
        }
    }

    @Test
    fun shouldNavigateToMainScreen_whenNavigationEventIsShowMainScreen() {
        navigationEvent.update { StartNavigationEvent.ShowMainScreen }

        start {
            verify { testNavigator.showMainScreen() }
        }
    }

    private fun givenStartScreen() {
        composeTestRule.setContent {
            MaterialTheme {
                StartScreen(
                    startNavigator = testNavigator,
                    viewModel = viewModel,
                )
            }
        }
    }
}
