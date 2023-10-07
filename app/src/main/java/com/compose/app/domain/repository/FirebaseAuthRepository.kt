package com.compose.app.domain.repository

import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import com.compose.app.data.firebase.model.FirebaseUserModel
import com.google.android.gms.auth.api.identity.SignInClient

interface FirebaseAuthRepository {

    suspend fun googleSignIn(
        clientId: String,
        oneTapClient: SignInClient,
    ): IntentSenderRequest

    suspend fun googleSignInWithIntent(
        oneTapClient: SignInClient,
        isResultSuccess: Boolean,
        resultData: Intent?,
    ): FirebaseUserModel

    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUserModel
    suspend fun signInUserWithEmailAndPassword(email: String, password: String): FirebaseUserModel

    fun currentSignedInUser() : FirebaseUserModel
    fun signOut() : Unit

}