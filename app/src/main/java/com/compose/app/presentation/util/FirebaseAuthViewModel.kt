package com.compose.app.presentation.util

import androidx.lifecycle.ViewModel
import com.compose.app.data.firebase.model.FirebaseUserModel
import com.compose.app.domain.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    fun currentSignedInUser(): FirebaseUserModel =
        authRepository.currentSignedInUser()

    fun signOut(): Unit = authRepository.signOut()
}