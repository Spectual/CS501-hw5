package com.example.dinner.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.dinner.data.DinnerViewModel
import com.example.dinner.navigation.Routes
import com.example.dinner.ui.screens.AddRecipeScreen
import com.example.dinner.ui.screens.DetailScreen
import com.example.dinner.ui.screens.HomeScreen
import com.example.dinner.ui.screens.SettingsScreen

@Composable
fun DinnerApp(viewModel: DinnerViewModel = viewModel()) {
    val navController = rememberNavController()
    val bottomDestinations = listOf(Routes.Home, Routes.Add, Routes.Settings)

    Scaffold(
        bottomBar = {
            DinnerBottomBar(
                navController = navController,
                destinations = bottomDestinations
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Home.route) {
                HomeScreen(
                    recipes = viewModel.recipes,
                    onRecipeSelected = { recipeId ->
                        navController.navigate("${Routes.Detail.route}/$recipeId") {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.Add.route) {
                AddRecipeScreen(
                    onSave = { title, ingredients, steps ->
                        val recipe = viewModel.addRecipe(title, ingredients, steps)
                        navController.navigate(Routes.detailRoute(recipe.id)) {
                            popUpTo(Routes.Add.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(
                route = Routes.DETAIL_WITH_ARG,
                arguments = listOf(
                    navArgument(Routes.RECIPE_ID_ARG) { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt(Routes.RECIPE_ID_ARG)
                val recipe = recipeId?.let(viewModel::recipeById)
                if (recipe != null) {
                    DetailScreen(recipe = recipe)
                } else {
                    Text(
                        text = "Recipe not found",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            composable(Routes.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
private fun DinnerBottomBar(
    navController: NavHostController,
    destinations: List<Routes>
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentRoute = currentDestination?.route
    val context = LocalContext.current

    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination.isRouteInHierarchy(destination.route),
                onClick = {
                    val isDetailScreen = currentRoute?.startsWith(Routes.Detail.route) == true
                    if (destination == Routes.Home && isDetailScreen) {
                        val popped = navController.popBackStack()
                        if (!popped) {
                            navController.navigate(destination.route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    } else {
                        navController.navigate(destination.route) {
                            launchSingleTop = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    }
                },
                icon = {
                    destination.icon?.let { icon ->
                        androidx.compose.material3.Icon(
                            imageVector = icon,
                            contentDescription = null
                        )
                    }
                },
                label = {
                    Text(text = context.getString(destination.labelResId))
                }
            )
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: String): Boolean =
    this?.hierarchy?.any { it.route?.startsWith(route) == true } == true
