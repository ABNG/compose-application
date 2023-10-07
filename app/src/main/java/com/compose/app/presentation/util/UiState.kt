package com.compose.app.presentation.util

sealed interface UiState<T> {
    class None<T> : UiState<T>
    class Loading<T> : UiState<T>
    data class Success<T>(val data: T?) : UiState<T>
    data class Error<T>(val errorMessage: String) : UiState<T>
}