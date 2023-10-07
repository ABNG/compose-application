package com.compose.app.domain.use_case

import com.compose.app.R
import javax.inject.Inject

class ValidatePassword @Inject constructor(){

    fun execute(password: String) : ValidationResult {
        if(password.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.blank_password_error_message
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.password_length_error_message
            )
        }
        val validPassword: Boolean =password.any{
            it.isDigit() && password.any { condition ->
                condition.isLetter()
            }
        }
        if(!validPassword){
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.invalid_format_password_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}