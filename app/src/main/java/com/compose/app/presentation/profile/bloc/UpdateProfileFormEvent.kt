package com.compose.app.presentation.profile.bloc

import android.net.Uri

sealed interface UpdateProfileFormEvent {
    data class UpdateImage(val image: Uri) : UpdateProfileFormEvent
    data class UpdateName(val name: String) : UpdateProfileFormEvent
    data class UpdateDOB(val dob: String) : UpdateProfileFormEvent
    data class UpdatePhoneNumber(val number: String) : UpdateProfileFormEvent
    object Submit : UpdateProfileFormEvent
}