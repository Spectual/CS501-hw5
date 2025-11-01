# CS501-hw5

## Q1 What's for Dinner?

```
./Dinner
```

## Q2 My Daily Hub

```
./DailyHub
```

## **How to Use the Apps**

1. Clone this repository:

```
git clone https://github.com/Spectual/CS501-hw5.git
```

1. Open the root folder in **Android Studio**.
2. Each folder (Badge, Contacts, Login, Scaffold, SplitScreen, Dinner) is an independent Android project.
3. To run a specific app:
   - Open the folder in Android Studio as a separate project, or
   - Use **File > Open** and choose the app folder you want.
   - Click ▶ Run to launch on emulator or device.

## **Explanation of the Apps**

### **1. What's for Dinner?**
- **Task:** Build a three-screen Compose app with bottom navigation, route arguments, and controlled back stack to browse, add, and view recipes.
- **Features:** Home screen `LazyColumn` of recipe names; detail screen showing title/ingredients/steps from the passed argument; add screen with a stateful form writing to an in-memory `ViewModel`; bottom bar for Home/Add/Settings tied into a `Scaffold`.
- **AI Usage:**

  - **How I used AI**

    - I used OpenAI ChatGPT assisted with scaffolding the navigation, Compose screen concepts, and ViewModel state design. ALso for composing recipe text examples.

    - Explain important screen and navigation concepts like stack behavior.

    - I provided sample classroom code and requested **step-by-step explanations** of why certain behaviors occur.

  - **Where AI misunderstood navigation:** 

    - The first suggestion set `popUpTo("home") { inclusive = true }`, which incorrectly removed Home from the back stack; I corrected it to `popUpTo("add") { inclusive = true }` so Home stays in place while avoiding duplicate Add screens.


### **2. My Daily Hub**

- **Task:** Build a Daily Hub bottom-nav app with Notes, Tasks, and Calendar tabs that keep state while navigating.
- **Features:** Single `NavHost` configured with sealed `HubRoute` entries; `BottomNavigation` tracks `currentBackStackEntryAsState()` and calls `navigate` with `popUpTo(start)`, `launchSingleTop`, and `restoreState = true`.
- **State:** `HubViewModel` stores note/task lists and drafts so entries, checkboxes, and form text persist across recompositions and configuration changes.
- **Transitions & Backstack:** Slide animations are driven by a `from` argument passed between destinations; manual test (Notes → Tasks → Calendar → Back → Back → Back) steps back through Tasks then Notes before exiting, with each tab restoring its prior content.
- **AI Usage:**

  - **How I used AI**

    - Consulted ChatGPT for the sealed routes/navigation argument pattern, animation helper scaffolding, and verifying state-hoisting decisions for the `ViewModel`.
    - Asked for clarification on Compose navigation flags to document observed back stack behaviour.

  - **Where AI misunderstood navigation:**

    - Initial suggestion split each tab into its own nav graph, which caused duplicate stacks and lost state; I rewrote the setup to keep a single graph with explicit `from` args that drive animations and preserve the shared back stack.

