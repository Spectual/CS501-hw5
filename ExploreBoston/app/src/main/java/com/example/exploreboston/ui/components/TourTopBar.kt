package com.example.exploreboston.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourTopBar(
    title: String,
    canNavigateBack: Boolean,
    showHomeAction: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (showHomeAction) {
                IconButton(onClick = onNavigateHome) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Go Home"
                    )
                }
            }
        }
    )
}
