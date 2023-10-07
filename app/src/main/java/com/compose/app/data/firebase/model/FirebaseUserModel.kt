package com.compose.app.data.firebase.model

import com.google.firebase.auth.FirebaseUser

data class FirebaseUserModel(
    val token : String? = null,
    val user : FirebaseUser? = null
)
