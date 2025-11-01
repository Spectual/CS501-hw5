package com.example.dinner.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dinner.data.Recipe

@Composable
fun HomeScreen(
    recipes: List<Recipe>,
    onRecipeSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (recipes.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No recipes yet",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Tap Add to create your first dinner plan.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipes) { recipe ->
                RecipeListCard(
                    recipe = recipe,
                    onClick = { onRecipeSelected(recipe.id) }
                )
            }
        }
    }
}

@Composable
private fun RecipeListCard(
    recipe: Recipe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${recipe.ingredients.size} ingredients - ${recipe.steps.size} steps",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DetailScreen(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                recipe.ingredients.forEachIndexed { index, ingredient ->
                    Text(
                        text = "${index + 1}. $ingredient",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        item {
            Text(
                text = "Steps",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                recipe.steps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun AddRecipeScreen(
    onSave: (title: String, ingredients: String, steps: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by rememberSaveable { mutableStateOf("") }
    var ingredients by rememberSaveable { mutableStateOf("") }
    var steps by rememberSaveable { mutableStateOf("") }

    val canSave = title.isNotBlank() && ingredients.isNotBlank() && steps.isNotBlank()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Add Recipe",
                style = MaterialTheme.typography.headlineSmall
            )
        }
        item {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Title") }
            )
        }
        item {
            OutlinedTextField(
                value = ingredients,
                onValueChange = { ingredients = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                label = { Text(text = "Ingredients (one per line)") },
                minLines = 6
            )
        }
        item {
            OutlinedTextField(
                value = steps,
                onValueChange = { steps = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                label = { Text(text = "Steps (one per line)") },
                minLines = 8
            )
        }
        item {
            Button(
                onClick = { onSave(title, ingredients, steps) },
                modifier = Modifier.fillMaxWidth(),
                enabled = canSave
            ) {
                Text(text = "Save Recipe")
            }
        }
    }
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Your recipes live in memory for this assignment. " +
                "In a production app you might sync with a database.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
