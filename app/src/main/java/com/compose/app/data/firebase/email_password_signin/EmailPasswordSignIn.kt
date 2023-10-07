package com.compose.app.data.firebase.email_password_signin

import com.compose.app.data.firebase.model.FirebaseUserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EmailPasswordSignIn @Inject constructor() {
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun createUserWithEmailAndPassword(email: String, password: String): FirebaseUserModel {
        return try {

            val user = auth.createUserWithEmailAndPassword(email, password).await().user
            FirebaseUserModel(
                user = user
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun signInUserWithEmailAndPassword(email: String, password: String): FirebaseUserModel {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            FirebaseUserModel(
                user = user
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}