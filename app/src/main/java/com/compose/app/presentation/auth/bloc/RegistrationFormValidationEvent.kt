package com.compose.app.presentation.auth.bloc

sealed interface RegistrationFormValidationEvent {
    data class NameChanged(val name: String) : RegistrationFormValidationEvent
    data class EmailChanged(val email: String) : RegistrationFormValidationEvent
    data class PasswordChanged(val password: String) : RegistrationFormValidationEvent
    data class ConfirmPasswordChanged(val password: String, val confirmPassword: String) :
        RegistrationFormValidationEvent

    data class AcceptedTermsChanged(val isAccepted: Boolean) : RegistrationFormValidationEvent
    data class Submit(val type: RegistrationType) : RegistrationFormValidationEvent
}

enum class RegistrationType {
   SIGN_UP, LOGIN
}
