package com.compose.app.presentation.auth.bloc

import androidx.annotation.StringRes

data class RegistrationFormValidationState(
    val name: String = "",
    @StringRes val nameErrorResId: Int? = null,
    val email: String = "",
    @StringRes val emailErrorResId: Int? = null,
    val password: String = "",
    @StringRes val passwordErrorResId: Int? = null,
    val confirmPassword: String = "",
    @StringRes val confirmPasswordErrorResId: Int? = null,
    val acceptedTerms: Boolean = false,
    @StringRes val acceptedTermsErrorResId: Int? = null,

    )