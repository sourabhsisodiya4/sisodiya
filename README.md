# Sisodiya WhatsApp Manager — Android starter project

This is a starter Android Studio project (Kotlin, native Android Views) for the Sisodiya WhatsApp Manager.

## Current prototype features
- Large-button Hindi/English interface
- Dashboard and navigation
- CSV contact import (simple comma-separated format; first row treated as header)
- Message composer
- Opens WhatsApp Business using a `wa.me` link for a single recipient; user reviews and taps Send

## Important limitations
- This is source code, not a compiled APK.
- Bulk/background auto-sending, WhatsApp inbox reading, delivery/read receipts, and automatic replies are not implemented. They are not safely/officially available through the regular WhatsApp Business App integration.
- CSV import is a starter parser, not a full Excel `.xlsx` importer. Export your Excel sheet as CSV first.
- Data is currently held in memory only and will not persist after closing the app. A database, consent/opt-out records, robust CSV parsing, campaign queue and tests are still needed before production use.

## Build APK
Open this folder in Android Studio on a computer, allow Gradle sync, then choose Build > Build Bundle(s) / APK(s) > Build APK(s). This environment did not have Android SDK/Gradle installed, so an APK could not be compiled here.

## CSV format
Save as CSV UTF-8 with columns such as `name,phone`. Include country code in phone numbers, e.g. `919876543210`.

## Build from a phone using GitHub Actions
1. Create a new GitHub repository from the GitHub mobile website/app.
2. Upload the contents of this ZIP (extract it first; include the `.github` folder).
3. Open the repository's **Actions** tab and select **Build Android APK**.
4. Tap **Run workflow** (or wait for the build after pushing to `main`).
5. When the run succeeds, open it and download the artifact named `Sisodiya-WhatsApp-Manager-debug-apk`.
6. Extract the artifact ZIP and install `app-debug.apk` on your Android phone. Android may ask you to allow installs from that source.
