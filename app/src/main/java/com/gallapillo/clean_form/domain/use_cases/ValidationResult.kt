package com.gallapillo.clean_form.domain.use_cases

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null
)