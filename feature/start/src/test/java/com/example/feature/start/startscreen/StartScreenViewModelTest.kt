package com.example.feature.start.startscreen

import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StartScreenViewModelTest {
    private lateinit var sut: StartViewModel

    @BeforeEach
    fun setUp() {
        sut = StartViewModel()
    }

    @Test
    fun `Should emit ShowMainScreen navigation event when invoke onGetStarted`() = runTest {
        sut.onGetStarted()

        sut.navigationEventFlow.value shouldBe StartNavigationEvent.ShowMainScreen
    }
}