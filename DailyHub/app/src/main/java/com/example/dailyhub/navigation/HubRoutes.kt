package com.example.dailyhub.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dailyhub.R

sealed class HubRoute(
    val base: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector,
    val order: Int
) {
    data object Notes : HubRoute("notes", R.string.nav_notes, Icons.Default.EditNote, 0)
    data object Tasks : HubRoute("tasks", R.string.nav_tasks, Icons.Default.CheckCircle, 1)
    data object Calendar : HubRoute("calendar", R.string.nav_calendar, Icons.Default.CalendarToday, 2)

    val routePattern: String = "$base?$FROM_KEY={$FROM_KEY}"

    fun buildRoute(from: HubRoute): String = "$base?$FROM_KEY=${from.base}"

    companion object {
        const val FROM_KEY = "from"

        val bottomBarItems = listOf(Notes, Tasks, Calendar)

        fun fromBase(base: String?): HubRoute? = when (base) {
            Notes.base -> Notes
            Tasks.base -> Tasks
            Calendar.base -> Calendar
            else -> null
        }

        fun fromDestination(route: String?): HubRoute? =
            route?.substringBefore("?")?.let(::fromBase)
    }
}
