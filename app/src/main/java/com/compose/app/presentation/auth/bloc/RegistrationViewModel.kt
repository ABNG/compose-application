package com.compose.app.presentation.auth.bloc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.firebase.model.UserModel
import com.compose.app.domain.repository.FirebaseAuthRepository
import com.compose.app.domain.use_case.ValidateAcceptedTerms
import com.compose.app.domain.use_case.ValidateConfirmPassword
import com.compose.app.domain.use_case.ValidateEmail
import com.compose.app.domain.use_case.ValidateName
import com.compose.app.domain.use_case.ValidatePassword
import com.compose.app.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateName: ValidateName,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateConfirmPassword: ValidateConfirmPassword,
    private val validateAcceptedTerms: ValidateAcceptedTerms,
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    var validationState by mutableStateOf(RegistrationFormValidationState())
        private set

    private val uiStateChannel = Channel<UiState<UserModel>>()
    val uiState = uiStateChannel.receiveAsFlow()


    fun onEvent(event: RegistrationFormValidationEvent) {
        when (event) {
            is RegistrationFormValidationEvent.AcceptedTermsChanged -> {
                validationState = validationState.copy(acceptedTerms = event.isAccepted)
            }

            is RegistrationFormValidationEvent.ConfirmPasswordChanged -> {
                validationState = validationState.copy(confirmPassword = event.confirmPassword)
            }

            is RegistrationFormValidationEvent.EmailChanged -> {
                validationState = validationState.copy(email = event.email)
            }

            is RegistrationFormValidationEvent.NameChanged -> {
                validationState = validationState.copy(name = event.name)
            }

            is RegistrationFormValidationEvent.PasswordChanged -> {
                validationState = validationState.copy(password = event.password)
            }

            is RegistrationFormValidationEvent.Submit -> {
                if(event.type== RegistrationType.SIGN_UP) {
                    submitSignUpData()
                }else{
                    submitLoginData()
                }
            }
        }
    }

    private fun submitSignUpData() {
        val nameResult = validateName.execute(validationState.name)
        val emailResult = validateEmail.execute(validationState.email)
        val passwordResult = validatePassword.execute(validationState.password)
        val confirmPasswordResult =
            validateConfirmPassword.execute(validationState.password, validationState.confirmPassword)
        val acceptedTermsResult = validateAcceptedTerms.execute(validationState.acceptedTerms)

        validationState = validationState.copy(
            nameErrorResId = nameResult.errorMessageResId,
            emailErrorResId = emailResult.errorMessageResId,
            passwordErrorResId = passwordResult.errorMessageResId,
            confirmPasswordErrorResId = confirmPasswordResult.errorMessageResId,
            acceptedTermsErrorResId = acceptedTermsResult.errorMessageResId
        )

        val hasError = listOf(
            nameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult,
            acceptedTermsResult
        ).any {
            !it.successful
        }
        if (!hasError) {
            viewModelScope.launch {
                uiStateChannel.send(UiState.Loading())
                try {
                    val userModel = authRepository.createUserWithEmailAndPassword(
                        validationState.email,
                        validationState.password
                    )
                    uiStateChannel.send(UiState.Success(userModel))
                } catch (e: Exception) {
                    uiStateChannel.send(UiState.Error(errorMessage = e.message.toString()))
                }
            }
        }
    }
    private fun submitLoginData() {
        val emailResult = validateEmail.execute(validationState.email)
        val passwordResult = validatePassword.execute(validationState.password)

        validationState = validationState.copy(
            emailErrorResId = emailResult.errorMessageResId,
            passwordErrorResId = passwordResult.errorMessageResId,
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any {
            !it.successful
        }
        if (!hasError) {
            viewModelScope.launch {
                uiStateChannel.send(UiState.Loading())
                try {
                    val userModel = authRepository.signInUserWithEmailAndPassword(
                        validationState.email,
                        validationState.password
                    )
                    uiStateChannel.send(UiState.Success(userModel))
                } catch (e: Exception) {
                    uiStateChannel.send(UiState.Error(errorMessage = e.message.toString()))
                }
            }
        }
    }
}