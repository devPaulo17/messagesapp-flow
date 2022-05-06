<h1 align="center">PostApp</h1>

<p align="center">
  <a href="https://devlibrary.withgoogle.com/products/android/repos/skydoves-pokedex"><img alt="Google" src="https://skydoves.github.io/badges/google-devlib.svg"/></a><br>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>

<p align="center">  
🗡️ PostsApp demonstrates modern Android development with Koin, Coroutines, Jetpack (ViewModel), and Material Design based on MVVM architecture.
</p>
</br>

## Download
Go to the [Releases](https://github.com/devPaulo17/weatherapp/releases) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)  + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Koin](https://insert-koin.io) for dependency injection.
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - DataBinding - Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- Testing
  - [Mockito](https://site.mockito.org) Tasty mocking framework for unit tests in Java
  - [Espresso](https://developer.android.com/training/testing/espresso) Use Espresso to write concise, beautiful, and reliable Android UI tests. To run UI test you should put it off this option from developer menu.
  - <img width="200" alt="Screenshot 2022-05-06 at 1 19 10" src="https://user-images.githubusercontent.com/36678251/167078256-448621bd-fa20-43c9-ae70-c81796f39142.png">

  - [CoroutinesTest](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/) Unit test with coroutines
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Swipe-to-Refresh](https://developer.android.com/training/swipe/add-swipe-interface) - The swipe-to-refresh user interface pattern. Which detects the vertical swipe, displays a distinctive progress bar, and triggers callback methods in your app
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [TransformationLayout](https://github.com/skydoves/transformationlayout) - implementing transformation motion animations.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components for building ripple animation, and CardView.

## MAD Score
![summary](https://user-images.githubusercontent.com/24237865/102366914-84f6b000-3ffc-11eb-8d49-b20694239782.png)
![kotlin](https://user-images.githubusercontent.com/24237865/102366932-8a53fa80-3ffc-11eb-8131-fd6745a6f079.png)

## Asynchronous flow
![mvvm_architecture](https://user-images.githubusercontent.com/36678251/167052898-b4072d58-1e82-4334-b0ad-b732caf55770.png)


## Architecture
WeatherApp is based on the MVVM architecture and the Repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
