plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("/Users/abubakarnawaz/Documents/ComposeApp")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }
    namespace = "com.compose.app"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}")
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${Version.lifecycleVersion}")
    implementation("androidx.activity:activity-compose:1.8.0")
    //compose
    implementation(platform("androidx.compose:compose-bom:2023.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
    //lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")
    //hilt
    implementation("com.google.dagger:hilt-android:${Version.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Version.hilt}")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //maps
    implementation("com.google.maps.android:maps-compose:2.14.1")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    //permissions
    implementation("com.google.accompanist:accompanist-permissions:0.33.1-alpha")
    //logging
    implementation("com.jakewharton.timber:timber:5.0.1")
    //coil
    implementation("io.coil-kt:coil-compose:${Version.coil}")
    implementation("io.coil-kt:coil-svg:${Version.coil}")
    //datastore
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    //firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    //auth
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    //networking
    implementation("com.squareup.retrofit2:retrofit:${Version.retrofit}")
    implementation("com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Version.okhttp}")
    //moshi
    implementation("com.squareup.moshi:moshi:${Version.moshi}")
    implementation("com.squareup.moshi:moshi-kotlin:${Version.moshi}")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}")
    //Room
    implementation("androidx.room:room-runtime:${Version.room}")
    implementation("androidx.room:room-ktx:${Version.room}")
    ksp("androidx.room:room-compiler:${Version.room}")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
kapt {
    correctErrorTypes = true
}