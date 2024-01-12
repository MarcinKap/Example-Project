package com.example.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.errors.UnknownNavGraphException
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    startRoute: Route,
    modifier: Modifier = Modifier,
) {
    DestinationsNavHost(
        engine = rememberAnimatedNavHostEngine(),
        navController = navController,
        navGraph = NavGraphs.root,
        startRoute = startRoute,
        modifier = modifier.fillMaxSize(),
        dependenciesContainerBuilder = {
            dependency(currentNavigator())
        },
    )
}

fun DependenciesContainerBuilder<*>.currentNavigator(): CommonNavGraphNavigator {
    return CommonNavGraphNavigator(
        navBackStackEntry.destination.navGraph(),
        navController,
    )
}

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        NavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }
    throw UnknownNavGraphException()
}
