package com.compose.app.domain.use_case

import android.util.Patterns
import com.compose.app.R
import javax.inject.Inject

class ValidateEmail @Inject constructor(){

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false, errorMessageResId = R.string.blank_email_error_message
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationResult(
                successful = false, errorMessageResId = R.string.invalid_email_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}