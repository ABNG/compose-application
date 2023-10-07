package com.compose.app.domain.use_case

import com.compose.app.R

import javax.inject.Inject

class ValidateConfirmPassword @Inject constructor(){

    fun execute(password: String, confirmPassword: String) : ValidationResult {
        if(password != confirmPassword){
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.no_match_confirm_password_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}