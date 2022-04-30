package com.gallapillo.clean_form.domain.use_cases

import android.util.Patterns

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password not matches"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

}