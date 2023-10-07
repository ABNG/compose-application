package com.compose.app.domain.use_case

import com.compose.app.R
import javax.inject.Inject

class ValidateAcceptedTerms @Inject constructor(){

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if(!acceptedTerms){
            return ValidationResult(successful = false,
                errorMessageResId = R.string.accepted_terms_error_message)
        }
        return ValidationResult(successful = true)
    }
}