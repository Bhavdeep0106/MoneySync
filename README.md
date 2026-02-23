# MoneySync - Local Two-User Finance Manager

MoneySync is a lightweight Android money management application designed for two users.
The app stores all financial data locally on each device and allows secure data sharing between users through an encrypted export link.

## Overview

MoneySync is built using:

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Clean Architecture principles
- Room Database
- Hilt Dependency Injection
- StateFlow for reactive state management

The application is designed for simplicity, privacy, and direct peer-to-peer data exchange without cloud dependency.

---

## Core Features

### 1. Local Storage
- All financial data is stored locally using Room Database.
- No external server dependency.
- Fully offline operation.

### 2. Two-User Sharing via Link
- Users can generate a secure export link.
- Link contains encrypted JSON payload of financial data.
- The receiving user can import data from the link.
- No cloud storage required.

### 3. Transaction Management
- Add income
- Add expense
- Edit transactions
- Delete transactions
- Assign categories
- Add notes
- Select dates

### 4. Category Management
- Create custom categories
- Assign colors and icons
- Edit or delete categories

### 5. Budget Tracking
- Set monthly budget per category
- Track usage progress
- Budget limit alerts

### 6. Dashboard
- Total income
- Total expenses
- Net balance
- Category breakdown visualization

---

## Architecture

The project follows Clean Architecture with three main layers:

- `presentation/`
- `domain/`
- `data/`

Flow:

`UI → ViewModel → UseCase → Repository → DataSource → Room Database`

This ensures:

- Separation of concerns
- Scalability
- Testability
- Maintainability

---

## Project Structure

```text
MoneySync/
│
├── app/
│   ├── presentation/
│   │   ├── screens/
│   │   ├── components/
│   │   ├── navigation/
│   │   └── viewmodel/
│   │
│   ├── domain/
│   │   ├── model/
│   │   ├── repository/
│   │   └── usecase/
│   │
│   ├── data/
│   │   ├── local/
│   │   │   ├── dao/
│   │   │   ├── entity/
│   │   │   └── database/
│   │   │
│   │   ├── repository/
│   │   └── mapper/
│   │
│   ├── di/
│   └── utils/
│
├── build.gradle
└── settings.gradle
```

---

## Data Sharing Mechanism

### Export Process

1. Serialize local database data into JSON.
2. Encrypt JSON using AES encryption.
3. Encode encrypted payload in Base64.
4. Generate shareable link using deep link scheme:

```text
moneysync://import?data=<encrypted_payload>
```

5. Share via:
   - WhatsApp
   - Telegram
   - Email
   - QR code

### Import Process

1. User clicks shared link.
2. App intercepts deep link.
3. Decode Base64 payload.
4. Decrypt JSON.
5. Merge or replace local database.

---

## Security Considerations

- AES encryption for exported data
- Optional PIN lock
- Biometric authentication
- No remote storage
- No internet permission required

---

## APK Distribution

To build APK:

```bash
./gradlew assembleRelease
```

Generated file:

```text
app/build/outputs/apk/release/app-release.apk
```

You can share the APK file directly with the second user.

Installation steps:

1. Send APK via WhatsApp, Google Drive, or AirDrop.
2. Enable "Install from unknown sources".
3. Install APK.
4. Both users can exchange financial data using share links.

---

## How to Share the App with Another User

- Option 1: Share APK directly
- Option 2: Upload APK to Google Drive and send download link
- Option 3: Publish privately on Play Console using internal testing

For private two-person usage, direct APK sharing is sufficient.

---

## Future Enhancements

- Multi-device sync option
- End-to-end encrypted backup file
- QR-based instant transfer
- Recurring transactions
- Advanced analytics
- Export to CSV or Excel

---

## Development Roadmap

### Phase 1
Local transaction management

### Phase 2
Secure encrypted link sharing

### Phase 3
Budget tracking and analytics

### Phase 4
Security hardening

---

## License

Private personal project.
