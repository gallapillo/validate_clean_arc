package com.gallapillo.clean_form.domain.use_cases

import android.util.Patterns

class ValidateTerms {

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "The terms not accepted"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

}