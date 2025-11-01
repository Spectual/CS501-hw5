# Dinner Navigation App

## Overview
Three-screen Jetpack Compose app for browsing dinner recipes. Navigation is handled with a single `NavHostController`, a sealed `Routes` definition, and bottom navigation for Home, Add, and Settings destinations. Recipes live in an in-memory `DinnerViewModel`, and new entries use route arguments (`detail/{recipeId}`) so the detail screen can resolve the selected recipe from the shared state.

### Screens
- **Home** - `LazyColumn` of recipe cards; tapping one runs `navigate("detail/$id")` with `launchSingleTop = true`.
- **Detail** - Shows title, ingredients, and steps for the `recipeId` argument pulled from `backStackEntry.arguments`.
- **Add Recipe** - Form that writes into the view model and navigates to the `Detail` screen with `popUpTo("add") { inclusive = true }` so the Add form stays single-instance.
- **Settings** - Simple info placeholder reachable from the bottom bar.

## Running The App
```
./gradlew :app:assembleDebug
```
Open the generated app in Android Studio or install the debug build on a device/emulator.

## Repository
- Git link: https://github.com/spectual/cs501-hw5-dinner

## AI Assistance Disclosure
- **What AI did** - Used ChatGPT (Codex CLI) to scaffold navigation, Compose screens, ViewModel state, and documentation.
- **Where it misunderstood navigation** - The first AI proposal tried to `popUpTo("home") { inclusive = true }`, which incorrectly cleared Home from the stack before pushing Detail. The final implementation instead `popUpTo("add") { inclusive = true }` so Home persists under Detail while still removing duplicate Add entries.
- **What I validated manually** - Adjusted bottom navigation handling, verified `launchSingleTop` is applied for each destination, and ensured route argument parsing aligns with the view model state.
