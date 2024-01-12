package com.example.feature.start.startscreen

sealed interface StartNavigationEvent {
    object Idle : StartNavigationEvent
    object ShowMainScreen : StartNavigationEvent
}
