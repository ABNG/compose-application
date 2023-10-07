package com.compose.app.presentation.profile.bloc

import android.net.Uri
import androidx.annotation.StringRes

data class UpdateProfileFormState(

    val imageUri: Uri? = null,
    @StringRes val imageUriErrorResId: Int? = null,
    val name: String = "",
    @StringRes val nameErrorResId: Int? = null,
    val dateOfBirth: String = "",
    @StringRes val dateOfBirthErrorResId: Int? = null,
    val phoneNumber: String = "",
    @StringRes val phoneNumberErrorResId: Int? = null,
)
