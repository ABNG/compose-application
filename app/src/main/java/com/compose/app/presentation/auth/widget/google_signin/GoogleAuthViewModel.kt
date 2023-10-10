package com.compose.app.presentation.auth.widget.google_signin

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.firebase.model.UserModel
import com.compose.app.domain.repository.FirebaseAuthRepository
import com.compose.app.presentation.util.UiState
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleAuthViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    private val uiStateChannel = Channel<UiState<UserModel>>()
    val uiState = uiStateChannel.receiveAsFlow()

    suspend fun googleSignIn(
        clientId: String,
        oneTapClient: SignInClient,
    ): Any {
        return try {
            authRepository.googleSignIn(
                clientId,
                oneTapClient
            )
        } catch (e: Exception) {
            e.printStackTrace()
            e.message.toString()
        }


    }

    fun googleSignInWithIntent(
        oneTapClient: SignInClient,
        isResultSuccess: Boolean,
        resultData: Intent?,
    ) {
        viewModelScope.launch {
            uiStateChannel.send(UiState.Loading());
            try {
                val UserModel = authRepository.googleSignInWithIntent(
                    oneTapClient,
                    isResultSuccess,
                    resultData
                )
                uiStateChannel.send(UiState.Success(UserModel))
            } catch (e: Exception) {
                e.printStackTrace()
                uiStateChannel.send(UiState.Error(errorMessage = e.message.toString()))
            }
        }
    }


}