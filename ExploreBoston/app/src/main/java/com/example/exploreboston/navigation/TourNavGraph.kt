package com.example.exploreboston.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.exploreboston.data.TourData
import com.example.exploreboston.ui.screens.CategoriesScreen
import com.example.exploreboston.ui.screens.DetailScreen
import com.example.exploreboston.ui.screens.HomeScreen
import com.example.exploreboston.ui.screens.ListScreen

@Composable
fun TourNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onUpdateTitle: (String) -> Unit,
    onNavigatedAwayFromHome: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = TourRoute.Home.route,
        modifier = modifier
    ) {
        composable(TourRoute.Home.route) {
            LaunchedEffect(Unit) { onUpdateTitle("Explore Boston") }
            HomeScreen(
                onStartTour = {
                    onNavigatedAwayFromHome()
                    navController.navigate(TourRoute.Categories.route)
                }
            )
        }
        composable(TourRoute.Categories.route) {
            LaunchedEffect(Unit) { onUpdateTitle("Choose a Category") }
            CategoriesScreen(
                categories = TourData.categories,
                onCategorySelected = { category ->
                    navController.navigate(TourRoute.List.createRoute(category.id))
                }
            )
        }
        composable(
            route = TourRoute.List.route,
            arguments = listOf(
                navArgument(TourRoute.List.CATEGORY_ID_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString(TourRoute.List.CATEGORY_ID_ARG)
                ?: return@composable
            val category = TourData.getCategory(categoryId) ?: return@composable
            val destinations = TourData.getDestinations(categoryId)

            LaunchedEffect(categoryId) { onUpdateTitle("All ${category.title}") }

            ListScreen(
                category = category,
                destinations = destinations,
                onDestinationSelected = { destination ->
                    navController.navigate(
                        TourRoute.Detail.createRoute(
                            categoryId = destination.categoryId,
                            locationId = destination.id
                        )
                    )
                }
            )
        }
        composable(
            route = TourRoute.Detail.route,
            arguments = listOf(
                navArgument(TourRoute.Detail.CATEGORY_ID_ARG) {
                    type = NavType.StringType
                },
                navArgument(TourRoute.Detail.LOCATION_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val categoryId =
                backStackEntry.arguments?.getString(TourRoute.Detail.CATEGORY_ID_ARG)
                    ?: return@composable
            val locationId =
                backStackEntry.arguments?.getInt(TourRoute.Detail.LOCATION_ID_ARG)
                    ?: return@composable
            val category = TourData.getCategory(categoryId) ?: return@composable
            val destination = TourData.getDestination(categoryId, locationId) ?: return@composable

            LaunchedEffect(destination.id) { onUpdateTitle(destination.name) }

            DetailScreen(
                categoryTitle = category.title,
                destination = destination
            )
        }
    }
}
