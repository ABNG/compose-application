package com.compose.app.data.repository

import android.app.Application
import com.compose.app.data.datastore.AppPreferences
import com.compose.app.data.datastore.dataStore
import com.compose.app.domain.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    private val context: Application
) : AppPreferencesRepository {
    private var appPref: AppPreferences? = null
    override suspend fun read(): AppPreferences {
        if (appPref == null) {
            appPref = context.dataStore.data.first()
        }
        return appPref!!
    }

    override suspend fun write(updateData: (AppPreferences) -> AppPreferences) {
        context.dataStore.updateData { appPref ->
            updateData(appPref)
        }
    }

    override fun readAsFlow(): Flow<AppPreferences> = context.dataStore.data
}