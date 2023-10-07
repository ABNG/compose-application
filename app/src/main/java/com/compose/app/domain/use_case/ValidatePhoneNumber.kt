package com.compose.app.domain.use_case

import com.compose.app.R
import javax.inject.Inject

class ValidatePhoneNumber @Inject constructor() {

    fun execute(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.blank_phone_number_error_message
            )
        } else if (phoneNumber.length < 11) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.phone_number_length_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}