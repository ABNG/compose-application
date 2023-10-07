package com.compose.app.data.repository

import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import com.compose.app.data.firebase.email_password_signin.EmailPasswordSignIn
import com.compose.app.data.firebase.google_signin.GoogleSignIn
import com.compose.app.data.firebase.model.FirebaseUserModel
import com.compose.app.domain.repository.FirebaseAuthRepository
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseGoogleSignIn: GoogleSignIn,
    private val firebaseEmailPasswordSignIn: EmailPasswordSignIn
) : FirebaseAuthRepository {

    private val auth: FirebaseAuth = Firebase.auth

    override suspend fun googleSignIn(
        clientId: String, oneTapClient: SignInClient
    ): IntentSenderRequest = firebaseGoogleSignIn.signIn(
        clientId, oneTapClient
    )


    override suspend fun googleSignInWithIntent(
        oneTapClient: SignInClient, isResultSuccess: Boolean, resultData: Intent?
    ): FirebaseUserModel = firebaseGoogleSignIn.signInWithIntent(
        oneTapClient, isResultSuccess, resultData
    )

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseUserModel =
        firebaseEmailPasswordSignIn.createUserWithEmailAndPassword(email, password)


    override suspend fun signInUserWithEmailAndPassword(
        email: String, password: String
    ): FirebaseUserModel =
        firebaseEmailPasswordSignIn.signInUserWithEmailAndPassword(email, password)

    override fun currentSignedInUser(): FirebaseUserModel =
        FirebaseUserModel(
            user = auth.currentUser
        )

    override fun signOut() = auth.signOut()

}