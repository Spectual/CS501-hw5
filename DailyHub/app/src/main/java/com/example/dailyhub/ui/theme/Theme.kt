package com.example.dailyhub.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Indigo40,
    onPrimary = Color.White,
    primaryContainer = Indigo90,
    onPrimaryContainer = Slate20,
    secondary = Teal40,
    onSecondary = Color.White,
    secondaryContainer = Teal90,
    onSecondaryContainer = Slate20,
    background = Slate90,
    onBackground = Slate20,
    surface = Color.White,
    onSurface = Slate20,
    surfaceVariant = Indigo90,
    onSurfaceVariant = Slate20
)

private val DarkColors = darkColorScheme(
    primary = Indigo90,
    onPrimary = Slate20,
    primaryContainer = Indigo40,
    onPrimaryContainer = Color.White,
    secondary = Teal90,
    onSecondary = Slate20,
    secondaryContainer = Teal40,
    onSecondaryContainer = Color.White,
    background = Slate20,
    onBackground = Indigo90,
    surface = Slate20,
    onSurface = Indigo90,
    surfaceVariant = Teal40,
    onSurfaceVariant = Teal90
)

@Composable
fun DailyHubTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = HubTypography,
        content = content
    )
}
