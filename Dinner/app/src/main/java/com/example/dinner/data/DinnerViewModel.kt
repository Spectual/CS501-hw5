package com.example.dinner.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicInteger

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>
)

class DinnerViewModel : ViewModel() {

    private val nextId = AtomicInteger(0)
    private val _recipes = mutableStateListOf(
        Recipe(
            id = nextId.getAndIncrement(),
            title = "Lemon Herb Salmon",
            ingredients = listOf(
                "2 salmon fillets",
                "2 tbsp olive oil",
                "1 lemon (zest + juice)",
                "1 tsp garlic powder",
                "Salt and pepper"
            ),
            steps = listOf(
                "Preheat oven to 400F (200C).",
                "Whisk lemon juice, zest, olive oil, garlic, salt, and pepper.",
                "Brush mixture over salmon and bake for 12 minutes.",
                "Finish under broiler for 2 minutes for crispy top."
            )
        ),
        Recipe(
            id = nextId.getAndIncrement(),
            title = "Chickpea Power Salad",
            ingredients = listOf(
                "1 can chickpeas, rinsed",
                "1 cup cherry tomatoes, halved",
                "1 cucumber, diced",
                "1/4 cup feta cheese",
                "2 tbsp balsamic vinaigrette"
            ),
            steps = listOf(
                "Combine chickpeas, tomatoes, cucumber, and feta in bowl.",
                "Drizzle with vinaigrette and toss gently.",
                "Chill for 10 minutes before serving."
            )
        )
    )

    val recipes: List<Recipe>
        get() = _recipes

    fun recipeById(id: Int): Recipe? = _recipes.firstOrNull { it.id == id }

    fun addRecipe(title: String, ingredientsEntry: String, stepsEntry: String): Recipe {
        val formattedIngredients = ingredientsEntry.split("\n")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        val formattedSteps = stepsEntry.split("\n")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        val recipe = Recipe(
            id = nextId.getAndIncrement(),
            title = title.trim(),
            ingredients = formattedIngredients,
            steps = formattedSteps
        )
        _recipes.add(recipe)
        return recipe
    }
}
