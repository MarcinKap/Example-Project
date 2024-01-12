package com.example.feature.main.mainscreen

sealed interface MainNavigationEvent {
    data object Idle : MainNavigationEvent
    data object Back : MainNavigationEvent
}
