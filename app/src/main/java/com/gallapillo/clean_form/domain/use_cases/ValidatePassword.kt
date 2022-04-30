package com.gallapillo.clean_form.domain.use_cases

import android.util.Patterns

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password need min a 8 characters"
            )
        }

        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password need to contain at one digit & letter"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}