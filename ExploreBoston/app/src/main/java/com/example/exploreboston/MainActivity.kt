package com.example.exploreboston

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.exploreboston.navigation.TourNavGraph
import com.example.exploreboston.navigation.TourRoute
import com.example.exploreboston.ui.components.TourTopBar
import com.example.exploreboston.ui.theme.ExploreBostonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploreBostonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExploreBostonApp()
                }
            }
        }
    }
}

@Composable
private fun ExploreBostonApp() {
    val navController = rememberNavController()
    var currentTitle by rememberSaveable { mutableStateOf("Explore Boston") }
    var backDisabledAfterHome by rememberSaveable { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val canNavigateBack = navController.previousBackStackEntry != null &&
        !(backDisabledAfterHome && currentRoute == TourRoute.Home.route)
    val showHomeAction = currentRoute != null && currentRoute != TourRoute.Home.route

    BackHandler(enabled = backDisabledAfterHome && currentRoute == TourRoute.Home.route) {
        // Once the stack has been cleared back to Home, ignore further back presses.
    }

    Scaffold(
        topBar = {
            TourTopBar(
                title = currentTitle,
                canNavigateBack = canNavigateBack,
                showHomeAction = showHomeAction,
                onNavigateBack = { navController.popBackStack() },
                onNavigateHome = {
                    backDisabledAfterHome = true
                    navController.navigate(TourRoute.Home.route) {
                        popUpTo(TourRoute.Home.route) { inclusive = true }
                    }
                }
            )
        }
    ) { innerPadding ->
        TourNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            onUpdateTitle = { newTitle ->
                if (currentTitle != newTitle) currentTitle = newTitle
            },
            onNavigatedAwayFromHome = {
                if (backDisabledAfterHome) backDisabledAfterHome = false
            }
        )
    }
}
