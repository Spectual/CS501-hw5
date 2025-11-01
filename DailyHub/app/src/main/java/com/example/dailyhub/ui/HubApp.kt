package com.example.dailyhub.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
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
import com.example.dailyhub.data.HubViewModel
import com.example.dailyhub.navigation.HubRoute
import com.example.dailyhub.ui.screens.CalendarScreen
import com.example.dailyhub.ui.screens.NotesScreen
import com.example.dailyhub.ui.screens.TasksScreen

@Composable
fun HubApp(viewModel: HubViewModel = viewModel()) {
    val navController = rememberNavController()
    val bottomDestinations = HubRoute.bottomBarItems

    Scaffold(
        bottomBar = {
            HubBottomBar(
                navController = navController,
                destinations = bottomDestinations
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HubRoute.Notes.routePattern,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = HubRoute.Notes.routePattern,
                arguments = listOf(
                    navArgument(HubRoute.FROM_KEY) {
                        type = NavType.StringType
                        defaultValue = HubRoute.Notes.base
                    }
                ),
                enterTransition = { enterTransitionFor(HubRoute.Notes) },
                exitTransition = { exitTransitionFor(HubRoute.Notes, targetState) },
                popEnterTransition = { popEnterTransitionFor(HubRoute.Notes, initialState) },
                popExitTransition = { popExitTransitionFor(HubRoute.Notes) }
            ) {
                NotesScreen(
                    notes = viewModel.notes,
                    draft = viewModel.noteDraft,
                    onDraftChanged = viewModel::onNoteDraftChanged,
                    onAddNote = viewModel::addNote
                )
            }
            composable(
                route = HubRoute.Tasks.routePattern,
                arguments = listOf(
                    navArgument(HubRoute.FROM_KEY) {
                        type = NavType.StringType
                        defaultValue = HubRoute.Tasks.base
                    }
                ),
                enterTransition = { enterTransitionFor(HubRoute.Tasks) },
                exitTransition = { exitTransitionFor(HubRoute.Tasks, targetState) },
                popEnterTransition = { popEnterTransitionFor(HubRoute.Tasks, initialState) },
                popExitTransition = { popExitTransitionFor(HubRoute.Tasks) }
            ) {
                TasksScreen(
                    tasks = viewModel.tasks,
                    draft = viewModel.taskDraft,
                    onDraftChanged = viewModel::onTaskDraftChanged,
                    onAddTask = viewModel::addTask,
                    onToggleTask = viewModel::toggleTask
                )
            }
            composable(
                route = HubRoute.Calendar.routePattern,
                arguments = listOf(
                    navArgument(HubRoute.FROM_KEY) {
                        type = NavType.StringType
                        defaultValue = HubRoute.Calendar.base
                    }
                ),
                enterTransition = { enterTransitionFor(HubRoute.Calendar) },
                exitTransition = { exitTransitionFor(HubRoute.Calendar, targetState) },
                popEnterTransition = { popEnterTransitionFor(HubRoute.Calendar, initialState) },
                popExitTransition = { popExitTransitionFor(HubRoute.Calendar) }
            ) {
                CalendarScreen()
            }
        }
    }
}

@Composable
private fun HubBottomBar(
    navController: NavHostController,
    destinations: List<HubRoute>
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentRoute = HubRoute.fromDestination(currentDestination?.route) ?: HubRoute.Notes

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        destinations.forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination.isRouteInHierarchy(destination.base),
                onClick = {
                    if (currentRoute != destination) {
                        val origin = currentRoute
                        navController.navigate(destination.buildRoute(origin)) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.labelResId)
                    )
                },
                label = {
                    Text(text = stringResource(destination.labelResId))
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: String): Boolean =
    this?.hierarchy?.any { it.route?.substringBefore("?") == route } == true

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransitionFor(
    targetRoute: HubRoute
): EnterTransition {
    val origin = targetState.arguments?.getString(HubRoute.FROM_KEY)?.let(HubRoute::fromBase)
    return enterTransitionForDirection(slideDirection(origin, targetRoute))
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransitionFor(
    currentRoute: HubRoute,
    targetState: NavBackStackEntry
): ExitTransition {
    val targetRoute = HubRoute.fromDestination(targetState.destination.route)
    return exitTransitionForDirection(slideDirection(currentRoute, targetRoute))
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransitionFor(
    returningRoute: HubRoute,
    initialState: NavBackStackEntry
): EnterTransition {
    val originRoute = HubRoute.fromDestination(initialState.destination.route)
    return enterTransitionForDirection(slideDirection(originRoute, returningRoute)?.opposite())
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransitionFor(
    currentRoute: HubRoute
): ExitTransition {
    val origin = targetState.arguments?.getString(HubRoute.FROM_KEY)?.let(HubRoute::fromBase)
    return exitTransitionForDirection(slideDirection(currentRoute, origin)?.opposite())
}

private fun slideDirection(from: HubRoute?, to: HubRoute?): AnimatedContentTransitionScope.SlideDirection? {
    if (from == null || to == null) return null
    return when {
        to.order > from.order -> AnimatedContentTransitionScope.SlideDirection.Left
        to.order < from.order -> AnimatedContentTransitionScope.SlideDirection.Right
        else -> null
    }
}

private fun AnimatedContentTransitionScope.SlideDirection.opposite(): AnimatedContentTransitionScope.SlideDirection =
    when (this) {
        AnimatedContentTransitionScope.SlideDirection.Left -> AnimatedContentTransitionScope.SlideDirection.Right
        AnimatedContentTransitionScope.SlideDirection.Right -> AnimatedContentTransitionScope.SlideDirection.Left
        AnimatedContentTransitionScope.SlideDirection.Up -> AnimatedContentTransitionScope.SlideDirection.Down
        AnimatedContentTransitionScope.SlideDirection.Down -> AnimatedContentTransitionScope.SlideDirection.Up
        else -> this
    }

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransitionForDirection(
    direction: AnimatedContentTransitionScope.SlideDirection?
): EnterTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Left ->
        slideInHorizontally(animationSpec = tween(250)) { fullWidth -> fullWidth }
    AnimatedContentTransitionScope.SlideDirection.Right ->
        slideInHorizontally(animationSpec = tween(250)) { fullWidth -> -fullWidth }
    AnimatedContentTransitionScope.SlideDirection.Up ->
        slideInVertically(animationSpec = tween(250)) { fullHeight -> fullHeight }
    AnimatedContentTransitionScope.SlideDirection.Down ->
        slideInVertically(animationSpec = tween(250)) { fullHeight -> -fullHeight }
    null -> fadeIn(animationSpec = tween(200))
    else -> fadeIn(animationSpec = tween(200))
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransitionForDirection(
    direction: AnimatedContentTransitionScope.SlideDirection?
): ExitTransition = when (direction) {
    AnimatedContentTransitionScope.SlideDirection.Left ->
        slideOutHorizontally(animationSpec = tween(220)) { fullWidth -> -fullWidth }
    AnimatedContentTransitionScope.SlideDirection.Right ->
        slideOutHorizontally(animationSpec = tween(220)) { fullWidth -> fullWidth }
    AnimatedContentTransitionScope.SlideDirection.Up ->
        slideOutVertically(animationSpec = tween(220)) { fullHeight -> -fullHeight }
    AnimatedContentTransitionScope.SlideDirection.Down ->
        slideOutVertically(animationSpec = tween(220)) { fullHeight -> fullHeight }
    null -> fadeOut(animationSpec = tween(200))
    else -> fadeOut(animationSpec = tween(200))
}
