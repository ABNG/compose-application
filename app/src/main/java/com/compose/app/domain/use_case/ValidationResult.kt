package com.compose.app.domain.use_case

import androidx.annotation.StringRes

data class ValidationResult(
    val successful: Boolean,
    @StringRes val errorMessageResId: Int? = null
)