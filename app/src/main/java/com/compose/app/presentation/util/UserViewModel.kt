package com.compose.app.presentation.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.app.data.firebase.model.FirebaseUserModel
import com.compose.app.data.firebase.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {

    var userModel: UserModel? by mutableStateOf(
        null
    )
        private set

    fun initUserModel(user: UserModel) {
        userModel = user
    }

    fun updateUserModel(name: String?, image: String?, dob: String?, phoneNumber: String?) {
        userModel = userModel!!.copy(
            user = userModel!!.user!!.copy(
                name = name,
                photoUrl = image,
                dob = dob,
                phoneNumber = phoneNumber
            )
        )
    }
}