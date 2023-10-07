package com.compose.app.data.firebase.google_signin

import android.app.Activity
import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import com.compose.app.data.firebase.model.FirebaseUserModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class GoogleSignIn @Inject constructor() {
    private val auth: FirebaseAuth = Firebase.auth
    suspend fun signIn(
        clientId: String,
        oneTapClient: SignInClient,
    ): IntentSenderRequest {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(clientId)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
        return try {
            val result = oneTapClient.beginSignIn(signInRequest).await().pendingIntent.intentSender

            IntentSenderRequest.Builder(
                result
            ).build()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e

        }

    }

    suspend fun signInWithIntent(
        oneTapClient: SignInClient,
        isResultSuccess: Boolean,
        resultData: Intent?,
    ): FirebaseUserModel {
        try {
            if (isResultSuccess) {
                val credential = oneTapClient.getSignInCredentialFromIntent(resultData)
                val idToken = credential.googleIdToken

                when {
                    idToken != null -> {
                        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                        try {
                            val user: FirebaseUser? =
                                auth.signInWithCredential(firebaseCredential).await().user
                            return FirebaseUserModel(
                                token = idToken,
                                user = user
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                           throw e
                        }
                    }

                    else -> {
                        throw Exception("No ID token!")
                    }
                }
            } else {
                throw Exception("Dialog Closed.")
            }
        } catch (e: ApiException) {
            e.printStackTrace()
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    throw Exception("Dialog Canceled.")
                }

                CommonStatusCodes.NETWORK_ERROR -> {
                    throw Exception("Network Error.")
                }

                else -> {
                    throw e
                }
            }
        }
    }
}