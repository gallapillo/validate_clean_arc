package com.gallapillo.clean_form.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.clean_form.domain.use_cases.ValidateEmail
import com.gallapillo.clean_form.domain.use_cases.ValidatePassword
import com.gallapillo.clean_form.domain.use_cases.ValidateRepeatedPassword
import com.gallapillo.clean_form.domain.use_cases.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult.Companion.success
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.security.SignatureException

class SignUpFormViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword= ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {

    var state by mutableStateOf(SignUpFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpValidationEvent) {
        when (event) {
            is SignUpValidationEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpValidationEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignUpValidationEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is SignUpValidationEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is SignUpValidationEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword
        )
        val termResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = passwordResult.errorMessage,
                termsError = termResult.errorMessage
            )
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}