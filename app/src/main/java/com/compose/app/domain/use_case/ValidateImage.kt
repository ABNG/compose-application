package com.compose.app.domain.use_case

import android.net.Uri
import com.compose.app.R
import javax.inject.Inject

class ValidateImage @Inject constructor() {

    fun execute(image: Uri?): ValidationResult {
        if (image == null) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.image_required_error_message
            )
        }
        return ValidationResult(successful = true)
    }
}