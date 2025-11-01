# AI Collaboration Notes

- I used an AI coding assistant to scaffold the Jetpack Compose navigation structure, write the screen composables, and to double check Kotlin syntax (files under `app/src/main/java/com/example/exploreboston`).
- I accepted AI-generated route helpers for `popUpTo` usage and adapted the data model that the AI proposed into the final `TourData` object.

## Where Navigation Needed Corrections

- The assistant originally suggested clearing the back stack every time a new destination was opened, which broke the expected back-stack depth. I adjusted the navigation logic so that `popUpTo` with `inclusive = true` is only triggered from the Home action in the top bar.
- The assistant also attempted to keep the back button disabled permanently after returning home; I added a flag (`backDisabledAfterHome`) that resets when the user starts a new tour so back navigation works again on the next cycle.
