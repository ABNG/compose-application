package com.compose.app.data.firebase.email_password_signin

import com.compose.app.data.firebase.model.FirebaseUserModel
import com.compose.app.data.firebase.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EmailPasswordSignIn @Inject constructor() {
    private val auth: FirebaseAuth = Firebase.auth

    suspend fun createUserWithEmailAndPassword(email: String, password: String): UserModel {
        return try {

            val user = auth.createUserWithEmailAndPassword(email, password).await().user
            UserModel(
                user = FirebaseUserModel(
                    name = user?.displayName,
                    email = user?.email,
                    photoUrl = user?.photoUrl?.toString()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun signInUserWithEmailAndPassword(email: String, password: String): UserModel {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            UserModel(
                user = FirebaseUserModel(
                    name = user?.displayName,
                    email = user?.email,
                    photoUrl = user?.photoUrl?.toString()
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}