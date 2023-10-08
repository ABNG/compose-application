package com.compose.app.presentation.util.widget.loading_dialog

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

@Parcelize
class DialogState : Parcelable {
    var opened by mutableStateOf(false)
        private set

    fun openDialog() {
        opened = true
    }

   internal fun closeDialog() {
        opened = false
    }
}

@Composable
fun rememberDialogState(): DialogState {
    return rememberSaveable { DialogState() }
}

