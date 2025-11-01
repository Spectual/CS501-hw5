package com.example.dinner.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dinner.R

/**
 * Routes sealed class keeps navigation destinations consistent across the app.
 */
sealed class Routes(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector? = null
) {
    data object Home : Routes("home", R.string.nav_home, Icons.Filled.Home)
    data object Add : Routes("add", R.string.nav_add, Icons.Filled.Add)
    data object Settings : Routes("settings", R.string.nav_settings, Icons.Filled.Settings)
    data object Detail : Routes("detail", R.string.nav_detail)

    companion object {
        const val RECIPE_ID_ARG = "recipeId"
        const val DETAIL_WITH_ARG = "detail/{$RECIPE_ID_ARG}"

        fun detailRoute(recipeId: Int) = "${Detail.route}/$recipeId"
    }
}
