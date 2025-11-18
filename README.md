# 🎬 TheMovieDB Android App

<div align="center">

<img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" style="pointer-events: none;">
<img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" style="pointer-events: none;">
<img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white" alt="Jetpack Compose" style="pointer-events: none;">
<img src="https://img.shields.io/badge/Clean%20Architecture-FF6F00?style=for-the-badge" alt="Clean Architecture" style="pointer-events: none;">

**Modern Android application - Discover movies and TV shows with TheMovieDB API**

[Features](#-features) • [Architecture](#clean-architecture) • [Installation](#-installation) • [Tech Stack](#tech-stack)

</div>

---

## 📱 About

**TheMovieDB** is a modern Android application that allows you to discover movies and TV shows using the <a href="https://www.themoviedb.org/" target="_blank" rel="noopener noreferrer">TheMovieDB API</a>. The app is built following **Clean Architecture** principles, offers a modern UI with **Jetpack Compose**, and uses the **MVVM** architecture pattern.

### ✨ Features

- 🎬 **Movie & TV Show Discovery** - Discover popular movies and TV shows
- 🔍 **Smart Search** - Find content with real-time search
- ⭐ **Favorites** - Add your favorite content to favorites
- 📄 **Detailed Information** - Comprehensive detail pages for each content
- 🔁 **Pagination** - Infinite scroll content loading
- 📱 **Modern UI** - Smooth interface designed with Jetpack Compose
- 🌙 **Theme Support** - Customizable theme system

---

## <a id="clean-architecture"></a>🏗️ Clean Architecture

This project is built following **Clean Architecture** principles. Dependencies between layers are correctly configured, and each layer focuses on its own responsibility.

### 📐 Architecture Structure

```
┌─────────────────────────────────────┐
│      Feature (Presentation)         │  ← UI, ViewModels, Screens
│  ────────────────────────────────   │
│  ✅ → Domain                        │
│  ✅ → Core                          │
│  ❌ → Data (NONE)                   │
└─────────────────────────────────────┘
           ↓
┌─────────────────────────────────────┐
│            Core                     │  ← Utilities, Theme, Navigation
│  ────────────────────────────────   │
│  ❌ → Domain (NONE)                 │
│  ❌ → Data (NONE)                   │
│  ❌ → Feature (NONE)                │
└─────────────────────────────────────┘
           ↓
┌─────────────────────────────────────┐
│            Data                     │  ← Repository Impl, Data Sources
│  ────────────────────────────────   │
│  ✅ → Domain                        │
│  ⚠️ → Core (Minimal)                │
│  ❌ → Feature (NONE)                │
└─────────────────────────────────────┘
           ↓
┌─────────────────────────────────────┐
│           Domain                    │  ← Business Logic, Use Cases
│  ────────────────────────────────   │
│  ❌ → Data (NONE)                   │
│  ❌ → Feature (NONE)                │
│  ❌ → Core (NONE)                   │
│  ✅ Only standard libraries         │
└─────────────────────────────────────┘
```

### 📦 Package Structure

```
com.kalay.themoviedb/
│
├── app/                    # Application layer
│   ├── MainActivity.kt     # Entry point
│   └── MyApplication.kt    # Application class
│
├── core/                   # Shared components
│   ├── di/                 # Dependency injection modules
│   ├── navigation/         # Navigation setup
│   ├── sharedpref/         # SharedPreferences wrapper
│   ├── theme/              # App theme & typography
│   ├── ui/                 # Reusable UI components
│   └── util/               # Utilities (Resource, JsonExt, etc.)
│
├── data/                   # Data layer
│   ├── di/                 # Data DI modules
│   ├── local/              # Local data sources (Room)
│   │   ├── dao/            # Data Access Objects
│   │   ├── datasource/     # Local data source implementations
│   │   ├── db/             # Room database
│   │   └── entity/         # Room entities
│   ├── mapper/             # Data mappers (DTO ↔ Entity)
│   ├── remote/             # Remote data sources (Retrofit)
│   │   ├── datasource/     # Remote data source implementations
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── interceptor/    # OkHttp interceptors
│   │   ├── response/       # API response models
│   │   └── service/        # Retrofit services
│   └── repository/         # Repository implementations
│
├── domain/                 # Domain layer (Business logic)
│   ├── di/                 # Domain DI modules
│   ├── enums/              # Domain enums
│   ├── mapper/             # Domain mappers
│   ├── model/              # Domain models (DTOs)
│   │   ├── local/          # Local domain models
│   │   └── remote/         # Remote domain models
│   ├── repository/         # Repository interfaces
│   └── usecase/            # Use cases (Business logic)
│       ├── local/          # Local use cases
│       └── remote/         # Remote use cases
│
└── feature/                # Feature modules
    ├── detail/             # Detail screen
    │   ├── navigation/     # Navigation routes
    │   ├── presentation/   # ViewModel & UI State
    │   └── ui/             # UI components
    ├── favorites/          # Favorites feature
    ├── main/               # Main screen
    ├── movies/             # Movies feature
    ├── splash/             # Splash screen
    └── tvshows/            # TV Shows feature
```

### 🔄 Dependency Rules

| Layer | Domain | Data | Feature | Core |
|-------|--------|------|---------|------|
| **Domain** | ✅ Self | ❌ NONE | ❌ NONE | ❌ NONE |
| **Data** | ✅ YES | ✅ Self | ❌ NONE | ⚠️ Minimal |
| **Feature** | ✅ YES | ❌ NONE | ✅ Self | ✅ YES |
| **Core** | ❌ NONE | ❌ NONE | ❌ NONE | ✅ Self |

**Explanation:**
- ✅ **YES** = Dependency exists and is in the correct direction
- ❌ **NONE** = No dependency (Clean Architecture principle)
- ⚠️ **Minimal** = Small dependency (acceptable)

### 🎯 Clean Architecture Principles

1. **Separation of Concerns** - Each layer focuses on its own responsibility
2. **Dependency Rule** - Dependencies point inward (outer layers depend on inner layers)
3. **Independence** - Domain layer is platform-independent
4. **Testability** - Each layer can be tested independently
5. **Maintainability** - Changes are isolated

---

## <a id="tech-stack"></a>🛠️ Tech Stack

### Core Technologies
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** Clean Architecture + MVVM
- **Dependency Injection:** Hilt (Dagger)

### Libraries & Tools

#### UI & Navigation
- **Jetpack Compose** - Modern declarative UI
- **Material 3** - Material Design components
- **Navigation Compose** - Type-safe navigation
- **Coil** - Image loading

#### Networking
- **Retrofit** - REST API client
- **OkHttp** - HTTP client with interceptors
- **Gson** - JSON serialization
- **Kotlinx Serialization** - Type-safe serialization

#### Local Storage
- **Room** - Local database
- **SharedPreferences** - Key-value storage

#### Architecture Components
- **ViewModel** - UI-related data holder
- **StateFlow** - Observable data holders
- **Coroutines** - Asynchronous programming
- **Flow** - Reactive streams

#### Dependency Injection
- **Hilt** - Dependency injection framework
- **KSP** - Kotlin Symbol Processing

---

## 🚀 Installation

### Requirements
- Android Studio Hedgehog (2023.1.1) or higher
- JDK 11 or higher
- Android SDK 21+ (Minimum SDK)
- Gradle 8.0+

### Installation Steps

1. **Clone the repository**
```bash
git clone https://github.com/HKalay/TheMovieDB.git
cd TheMovieDB
   ```

2. **Configure API Key**
   
   Add your TheMovieDB API key to the `local.properties` file:
   ```properties
   API_KEY=your_api_key_here
   ```
   
   > 💡 To get an API key, sign up at <a href="https://www.themoviedb.org/settings/api" target="_blank" rel="noopener noreferrer">TheMovieDB</a>.

3. **Open the project**
   - Open the project in Android Studio
   - Wait for Gradle sync to complete

4. **Run the app**
```bash
./gradlew clean build
```
- Run on an emulator or physical device

---

## 🏛️ Architecture Details

### Domain Layer
- **Repository Interfaces** - Contracts for the data layer
- **Use Cases** - Single responsibility units of business logic
- **Domain Models** - Business entities (DTOs)
- **Mappers** - Conversions between domain models

### Data Layer
- **Repository Implementations** - Implementations of domain repository interfaces
- **Data Sources** - Remote (API) and Local (Database) data sources
- **Mappers** - Conversion from API DTOs to Domain models
- **Entities** - Room database entities

### Presentation Layer (Feature)
- **ViewModels** - UI state management and business logic coordination
- **UI State** - Observable state classes
- **Composables** - Jetpack Compose UI components
- **Navigation** - Type-safe navigation routes

### Core Layer
- **Utilities** - Resource wrapper, JSON extensions, ViewModel extensions
- **Theme** - App-wide theme configuration
- **Navigation** - Global navigation setup
- **DI Modules** - Shared dependency injection modules

---

## 🔐 Security

- ✅ API key is stored in `local.properties` file (not committed to git)
- ✅ API key is used securely through BuildConfig
- ✅ Code is protected in release builds with ProGuard rules

---

## 📄 License

This project is developed for educational purposes.

---

## 👤 Author

**Hamdullah KALAY**  

- 🌐 GitHub: <a href="https://github.com/HKalay" target="_blank" rel="noopener noreferrer">@HKalay</a>
- 📧 Email: hamdullahkly@gmail.com
- 💼 LinkedIn: <a href="https://www.linkedin.com/in/hamdullah-kalay-432647134/" target="_blank" rel="noopener noreferrer">Hamdullah KALAY</a>

---

## 🙏 Acknowledgments

- <a href="https://www.themoviedb.org/" target="_blank" rel="noopener noreferrer">TheMovieDB</a> - API provider
- <a href="https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html" target="_blank" rel="noopener noreferrer">Clean Architecture</a> - Robert C. Martin
- Android Developer Community

---

<div align="center">

**⭐ If you liked this project, don't forget to give it a star! ⭐**

Made with ❤️ using Clean Architecture

</div>
