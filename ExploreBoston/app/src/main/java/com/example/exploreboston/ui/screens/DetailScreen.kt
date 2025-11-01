package com.example.exploreboston.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.exploreboston.data.Destination

@Composable
fun DetailScreen(
    categoryTitle: String,
    destination: Destination
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = destination.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = categoryTitle,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = destination.headline,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = destination.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Find it at: ${destination.address}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Location ID: ${destination.id}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
