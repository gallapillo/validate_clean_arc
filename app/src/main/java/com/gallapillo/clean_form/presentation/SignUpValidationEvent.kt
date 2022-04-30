package com.gallapillo.clean_form.presentation

sealed class SignUpValidationEvent {
    data class EmailChanged(val email: String): SignUpValidationEvent()
    data class PasswordChanged(val password: String): SignUpValidationEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): SignUpValidationEvent()

    data class AcceptTerms(val isAccepted: Boolean): SignUpValidationEvent()
    object Submit: SignUpValidationEvent()
}
