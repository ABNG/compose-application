package com.compose.app.domain.use_case

import com.compose.app.R
import javax.inject.Inject

class ValidateDOB @Inject constructor() {

    fun execute(dob: String): ValidationResult {
        if (dob.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.blank_dob_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}