 # Ecommerce Application
 ### 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧

## Screenshots
<details>
 <summary>Click to expand</summary>

<div><video src="https://github.com/ABNG/compose-application/assets/44497582/073f5653-33a9-43a9-9021-8f11bceae20f" /></div>
<div><video src="https://github.com/ABNG/compose-application/assets/44497582/0c2524ed-f063-4cc3-8821-17be6970791d" /></div>
</details>


 ## Features
 * MVVM Architecture + Repository design Pattern.
 * Jetpack Libraries and Architecture Components.
 
 ## Prerequisites
 
 Add your [fakeapi.platzi](https://fakeapi.platzi.com/en/rest/introduction) API key in the `local.properties` file:
 ```
 MAPS_API_KEY=YOUR_API_KEY
 BASE_URL=YOUR_BASE_URL
 AUTH_TOKEN=ANY_DUMMY_VALUE
 ```
 
 ## Libraries
 * [Android Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose) Android's modern toolkit for building native UI.
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
    * [Navigation-Compose](https://developer.android.com/jetpack/compose/navigation/) The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component's infrastructure and features.
    * [Google Maps](https://developers.google.com/maps/documentation/android-sdk/maps-compose) Google Maps library for compose
 * [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
 * [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is a state-holder observable flow that emits the current and new state updates to its collectors.
 * [Coil-compose](https://coil-kt.github.io/coil/compose/) An image loading library for Android backed by Kotlin Coroutines.
 * [Retrofit](https://square.github.io/retrofit/) is a Type-safe HTTP client for Android, Java and Kotlin by Square.
 * [Moshi](https://github.com/square/moshi) is a modern JSON library for Android and Java. It makes it easy to parse JSON format data.
 * [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses.
 * [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android.
 * [Timber](https://github.com/JakeWharton/timber) A logger with a small, extensible API which provides utility on top of Android's normal Log class.
 * [Lottie](https://github.com/airbnb/lottie-android) a mobile library for Android and iOS that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile!.
 * [Permissions](https://google.github.io/accompanist/permissions/) A library which provides Android runtime permissions support for Jetpack Compose.
 * [Firebase Auth (Google and Email/Password)](https://firebase.google.com/docs/auth)  let your users authenticate with Firebase.
 * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
