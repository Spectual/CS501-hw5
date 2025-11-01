package com.example.exploreboston.navigation

sealed class TourRoute(val route: String) {
    data object Home : TourRoute("home")
    data object Categories : TourRoute("categories")
    data object List : TourRoute("list/{categoryId}") {
        const val CATEGORY_ID_ARG = "categoryId"
        fun createRoute(categoryId: String): String = "list/$categoryId"
    }
    data object Detail : TourRoute("detail/{categoryId}/{locationId}") {
        const val CATEGORY_ID_ARG = "categoryId"
        const val LOCATION_ID_ARG = "locationId"
        fun createRoute(categoryId: String, locationId: Int): String =
            "detail/$categoryId/$locationId"
    }
}
