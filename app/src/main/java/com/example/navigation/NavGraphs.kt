package com.example.navigation

import com.example.feature.main.mainscreen.composable.destinations.MainScreenDestination
import com.example.feature.start.startscreen.composable.destinations.StartScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {
    val start = object : NavGraphSpec {
        override val route = "start"

        override val startRoute = StartScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            StartScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val main = object : NavGraphSpec {
        override val route = "main"

        override val startRoute = MainScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            MainScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = start
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

        override val nestedNavGraphs = listOf(
            start,
            main,
        )
    }
}
