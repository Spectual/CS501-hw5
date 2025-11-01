## Q2 – My Daily Hub

- Notes, Tasks, and Calendar screens share a single `NavHost`; bottom navigation highlights the active route via `currentBackStackEntryAsState()`.
- Routes are defined with sealed `HubRoute` objects; navigation calls use `popUpTo`, `launchSingleTop`, and `restoreState = true` so each tab keeps its last scroll/draft state.
- Each screen reads its data from `HubViewModel`, so note/task drafts and checkbox states persist across recompositions and configuration changes.
- Screen changes animate with a directional slide that is driven by the `from` argument passed between destinations; lateral motion reflects the tab ordering.

### Back stack behaviour (manual test on emulator)
- Start on Notes → tap Tasks → tap Calendar → Back returns to Tasks, Back again returns to Notes, final Back exits the app.
- Revisiting a tab restores its previous content (e.g., unfinished note draft remains, completed tasks stay checked) because navigation restores saved state.

### AI collaboration
- Used AI to scaffold the initial navigation setup and transition helpers; the assistant first suggested separate nav graphs per tab, which broke shared back stack expectations.
- Adjusted the implementation to keep one `NavHost` with explicit `from` arguments so transitions stay consistent and the back button follows the documented flow.
