# Internal README

This document is for contributors and maintainers of the Calculator app. It outlines architecture, important functions, testing strategy, conventions, and dev workflows.

> Keep this file private/internal. Do not publish externally.

## Architecture
- Pattern: MVVM
- State holder: `CalculatorViewModel`
- UI: Compose (`CalculatorScreen`, `CalculatorButtons`)
- Actions: `CalculatorAction` sealed class
- Theme: Material3, `CalculatorTheme`

### Data Flow
1. UI emits actions via `CalculatorButtons` into `CalculatorViewModel.onAction(...)`.
2. ViewModel mutates state (`display`) and internal fields (`operand1`, `operator`).
3. `CalculatorScreen` observes state and renders UI.

## Important Functions (Core Logic)
Located in `CalculatorViewModel.kt`:

- `var display = mutableStateOf("0")`
  - Single source of truth for the calculator output. UI reads this.

- `fun onAction(action: CalculatorAction)`
  - Central dispatcher of UI events.
  - Routes to private handlers based on action type.

- `private fun appendNumber(number: String)`
  - Appends a digit to the current display, replacing leading "0".
  - Assumes numeric `number` ("0".."9"). Validation is handled at button construction.

- `private fun setOperator(op: String)`
  - Captures the current display as `operand1`, stores `operator`, resets display to "0".
  - Supported ops: `+`, `-`, `*`, `/`.

- `private fun calculate()`
  - Computes `operand1 (operator) operand2`, where `operand2` is current display.
  - Division-by-zero returns `0.0`.
  - Clears `operand1` and `operator` after computing.

- `private fun clear()`
  - Resets `display` to "0" and clears internal state.

## UI Contracts
- `CalculatorScreen(viewModel: CalculatorViewModel)`
  - Displays `display` (tag: `display`).
  - Contains the keypad: `CalculatorButtons(viewModel)`.

- `CalculatorButtons(viewModel: CalculatorViewModel)`
  - Emits actions for digits (0..9), operators (+, -, *, /), equals (=), and clear (C).
  - Button tags: `btn_0`, `btn_1`, ..., `btn_+`, `btn_-`, `btn_*`, `btn_/`, `btn_=`, `btn_C`.

## Testing Strategy
- JVM Unit Tests
  - `CalculatorViewModelTest`: happy paths + edge cases (equals without operator, divide by zero, clear state)
  - `CalculatorActionTest`: sealed action payloads

- Instrumented Compose UI Tests
  - `MainActivityTest`: top-level flows (addition, clear)
  - `CalculatorScreenTest`: screen-level behavior (digit entry, operator reset, equals, divide by zero, clear)
  - `CalculatorButtonsExistenceTest`: presence of all keypad buttons by tag

### Running Tests
```powershell
# Unit tests
./gradlew.bat test --no-daemon --console=plain

# Instrumented tests (requires emulator/device)
./gradlew.bat connectedAndroidTest --no-daemon --console=plain
```

## Conventions
- KDoc for all public classes/functions; concise comments for private ones when logic is non-trivial.
- Use `testTag` for any UI element that tests must target reliably.
- Prefer immutable parameters and explicit state transitions.

## Adding Features (Examples)
- Decimal support: introduce `CalculatorAction.Decimal` and update parsing/formatting.
- Unary ops (e.g., +/-): add new actions and handlers in the ViewModel, ensure clear UX rules.
- Chained operations: decide whether pressing an operator after operand2 triggers calculation or changes pending op.

## Troubleshooting
- Ambiguous node matches in Compose tests: switch to `onNodeWithTag(...)` with stable tags.
- Flaky UI tests: add `composeRule.waitUntil` for async state changes (not required here but useful at scale).
- Build failures on older SDKs: ensure compile/target SDKs in `app/build.gradle.kts` match the environment.

## Release/CI Notes
- Consider adding CI to run `test` and `connectedAndroidTest` on PRs.
- Enable codecov/JaCoCo in Gradle if code coverage tracking is desired.

