package com.compose.app.data.firebase.model

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.compositionLocalOf
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val token: String? = null,
    val user: FirebaseUserModel? = null
) : Parcelable

@Parcelize
data class FirebaseUserModel(
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val dob: String? = null,
    val phoneNumber: String? = null
) : Parcelable

val LocalUserModel = compositionLocalOf { UserModel() }

fun UserModel.toJson(): String {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(UserModel::class.java).lenient()
    return jsonAdapter.toJson(this)
}

fun String.fromJson(): UserModel {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(UserModel::class.java).lenient()
    return jsonAdapter.fromJson(this)!!
}