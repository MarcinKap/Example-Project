package com.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.design.theme.ExampleTheme
import com.example.navigation.AppNavigation
import com.example.navigation.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExampleTheme {
                navController = rememberNavController()

                Scaffold { padding ->
                    AppNavigation(
                        navController = navController,
                        startRoute = NavGraphs.start,
                        modifier = Modifier.padding(padding),
                    )
                }
            }
        }
    }
}
