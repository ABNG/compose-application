package com.compose.app.domain.use_case

import com.compose.app.R
import javax.inject.Inject

class ValidateName @Inject constructor(){

    fun execute(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.blank_name_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}