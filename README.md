# Android Usage Tracker

This is an Android application built with Kotlin and Jetpack Compose that tracks application usage statistics and displays them in a line graph. This project was generated using the Gemini CLI.

## Prerequisites

- Android Studio (latest version recommended)
- An Android device or emulator running API level 26 or higher

## How to Build and Run

### 1. Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Anaswar-ash/Kotlin-code-Gemini-CLI.git
    ```
2.  **Open in Android Studio:**
    -   Start Android Studio.
    -   Select "Open an existing project".
    -   Navigate to and select the cloned project folder.

3.  **Gradle Sync:**
    -   Wait for Android Studio to download and sync all the project dependencies. This may take a few minutes.

### 2. Build the APK

1.  Once the project has successfully synced, go to the menu bar.
2.  Select `Build > Build Bundle(s) / APK(s) > Build APK(s)`.
3.  Android Studio will build the application, and you can find the output APK file in `app/build/outputs/apk/debug/app-debug.apk`.

### 3. Install and Grant Permissions

1.  **Install the APK:**
    -   Install the `app-debug.apk` file onto your Android device.

2.  **Launch the App:**
    -   Open the app named "AndroidUsageTracker".

3.  **Grant Permission:**
    -   Upon first launch, the app will inform you that it needs "Usage data access" permission.
    -   Click the **"Open Settings"** button. You will be taken to your device's system settings.
    -   Find "AndroidUsageTracker" in the list and grant it permission to access usage data.
    -   Press the back button to return to the app.

4.  **View the Graph:**
    -   The app will now load and display a line graph of the top 5 most used applications from the last 24 hours.