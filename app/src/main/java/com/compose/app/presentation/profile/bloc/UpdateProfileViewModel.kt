package com.compose.app.presentation.profile.bloc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.app.domain.use_case.ValidateDOB
import com.compose.app.domain.use_case.ValidateImage
import com.compose.app.domain.use_case.ValidateName
import com.compose.app.domain.use_case.ValidatePhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val validateImage: ValidateImage,
    private val validateName: ValidateName,
    private val validateDOB: ValidateDOB,
    private val validatePhoneNumber: ValidatePhoneNumber,
) : ViewModel() {

    var state by mutableStateOf(UpdateProfileFormState())
        private set

    fun onEvent(event: UpdateProfileFormEvent) {
        when (event) {
            is UpdateProfileFormEvent.UpdateImage -> {
                state = state.copy(
                    imageUri = event.image
                )
            }

            is UpdateProfileFormEvent.UpdateName -> {
                state = state.copy(
                    name = event.name
                )
            }

            is UpdateProfileFormEvent.UpdateDOB -> {
                state = state.copy(
                    dateOfBirth = event.dob
                )
            }

            is UpdateProfileFormEvent.UpdatePhoneNumber -> {
                state = state.copy(
                    phoneNumber = event.number
                )
            }

            UpdateProfileFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val imageResult = validateImage.execute(state.imageUri)
        val nameResult = validateName.execute(state.name)
        val dobResult = validateDOB.execute(state.dateOfBirth)
        val phoneNumberResult = validatePhoneNumber.execute(state.phoneNumber)

        state = state.copy(
            imageUriErrorResId = imageResult.errorMessageResId,
            nameErrorResId = nameResult.errorMessageResId,
            dateOfBirthErrorResId = dobResult.errorMessageResId,
            phoneNumberErrorResId = phoneNumberResult.errorMessageResId
        )

        val hasNoError = listOf(
            imageResult,
            nameResult,
            dobResult,
            phoneNumberResult
        ).all {
            it.successful
        }

        if (hasNoError) {
            //do something here
        }

    }
}