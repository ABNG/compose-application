package com.compose.app.data.datastore

import android.content.Context
import androidx.datastore.dataStore

//app-preferences.pb
val Context.dataStore by dataStore("app-preferences.json", AppPreferencesSerializer)