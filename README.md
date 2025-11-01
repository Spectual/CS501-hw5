# CS501-hw5

## Q1 What's for Dinner?

```
./Dinner
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




### AI Usage

**How I used AI**

- I used OpenAI ChatGPT assisted with scaffolding the navigation, Compose screen concepts, and ViewModel state design. ALso for composing recipe text examples.

- Explain important screen and navigation concepts like stack behavior.
- I provided sample classroom code and requested **step-by-step explanations** of why certain behaviors occur.

**Where AI misunderstood navigation:** 

The first suggestion set `popUpTo("home") { inclusive = true }`, which incorrectly removed Home from the back stack; I corrected it to `popUpTo("add") { inclusive = true }` so Home stays in place while avoiding duplicate Add screens.

**What I kept**

- I used the **conceptual explanations** and **ui improvement suggestions** to refine my own implementation.
- I reused short snippets but **rewrote them in my own structure** to fit assignment requirements.

**What I verified:** 

* Ensured bottom navigation pops the detail page when returning Home, confirmed `launchSingleTop` usage.
