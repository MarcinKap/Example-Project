package com.example.feature.start.startscreen

import androidx.lifecycle.ViewModel
import com.example.feature.start.startscreen.StartNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor() : ViewModel() {

    private val _navigationEventFlow =
        MutableStateFlow<StartNavigationEvent>(StartNavigationEvent.Idle)
    val navigationEventFlow: StateFlow<StartNavigationEvent> = _navigationEventFlow.asStateFlow()

    fun onGetStarted() {
        navigateTo(StartNavigationEvent.ShowMainScreen)
    }

    fun onNavigationHandled() {
        navigateTo(StartNavigationEvent.Idle)
    }

    private fun navigateTo(event: StartNavigationEvent) {
        _navigationEventFlow.value = event
    }
}
