package com.compose.app.presentation.util

import androidx.lifecycle.ViewModel
import com.compose.app.data.datastore.AppPreferences
import com.compose.app.domain.repository.AppPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AppPreferencesViewModel @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) : ViewModel() {


    suspend fun read(): AppPreferences = appPreferencesRepository.read()


    suspend fun write(updateData: (AppPreferences) -> AppPreferences) =
        appPreferencesRepository.write(updateData)


    fun readAsFlow(): Flow<AppPreferences> = appPreferencesRepository.readAsFlow()

}