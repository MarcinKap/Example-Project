package com.example.navigation

import androidx.navigation.NavController
import com.example.feature.main.mainscreen.MainNavigator
import com.example.feature.start.startscreen.StartNavigator
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.utils.startDestination

@Suppress("TooManyFunctions")
class CommonNavGraphNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController,
) :
    StartNavigator,
    MainNavigator {
    override fun showMainScreen() {
        navController.navigate(NavGraphs.main) {
            popUpTo(navGraph.startDestination.route) {
                inclusive = true
            }
        }
    }

    override fun onBackPressed() {
        navController.navigateUp()
    }
}
