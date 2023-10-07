package com.compose.app.domain.repository

import com.compose.app.data.datastore.AppPreferences
import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    suspend fun read(): AppPreferences

    suspend fun write(updateData: (AppPreferences) -> AppPreferences)

    fun readAsFlow(): Flow<AppPreferences>
}