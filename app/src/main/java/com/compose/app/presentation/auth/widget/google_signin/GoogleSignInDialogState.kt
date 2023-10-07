package com.compose.app.presentation.auth.widget.google_signin

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.android.parcel.Parcelize

@Parcelize
class GoogleSignInDialogState : Parcelable{
    var opened by mutableStateOf(false)
    private set
    fun openDialog() {
        opened = true
    }
    internal fun closeDialog(){
        opened = false
    }
}

@Composable
fun rememberGoogleSignInDialogState(): GoogleSignInDialogState {
    return rememberSaveable {
        GoogleSignInDialogState()
    }
}