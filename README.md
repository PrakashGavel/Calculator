# Calculator (Android, Jetpack Compose)

A simple calculator app built with Kotlin, Jetpack Compose, and MVVM (ViewModel). It supports basic operations (+, -, *, /), clear, and an accessible UI with test tags for stable UI testing.

## Features
- Basic arithmetic: addition, subtraction, multiplication, division
- Division-by-zero handled (returns 0.0)
- Clear (C) resets the state
- MVVM with a lightweight `CalculatorViewModel`
- Jetpack Compose UI
- Instrumented UI tests and JVM unit tests

## Getting Started
1. Requirements
   - Android Studio (Giraffe/Flamingo or newer)
   - JDK 11
   - Android SDK 24+
2. Clone and open the project in Android Studio.
3. Build and run on an emulator or device (API 24+).

## Build
```powershell
# From project root on Windows
./gradlew.bat assembleDebug
```

## Run
- Launch `MainActivity` from Android Studio, or:
```powershell
./gradlew.bat installDebug ; adb shell am start -n com.example.calculator/.MainActivity
```

## Testing
- JVM unit tests:
```powershell
./gradlew.bat test --no-daemon --console=plain
```
- Instrumented UI tests (requires emulator/device):
```powershell
./gradlew.bat connectedAndroidTest --no-daemon --console=plain
```

### Test Coverage Overview
- Unit tests
  - `CalculatorViewModelTest` covers digits, operators, equals, clear, and edge cases (divide by zero, equals without operator)
  - `CalculatorActionTest` validates sealed action payloads
- Instrumented tests
  - `MainActivityTest` validates high-level flows with stable tags
  - `CalculatorScreenTest` exercises Composable interactions and edge cases
  - `CalculatorButtonsExistenceTest` confirms all keys exist

## Code Style & Lint
- Kotlin, Compose idioms
- KDoc on key classes and functions


