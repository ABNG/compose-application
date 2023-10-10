package com.compose.app.presentation.auth.widget.google_signin

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.app.data.firebase.model.UserModel
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.loading_dialog.LoadingDialogWidget
import com.compose.app.presentation.util.widget.loading_dialog.rememberDialogState
import com.google.android.gms.auth.api.identity.Identity


@Composable
fun GoogleSignInWidget(
    googleAuthViewModel: GoogleAuthViewModel = hiltViewModel(),
    state: GoogleSignInDialogState,
    clientId: String,
    onUserDataReceived: (UserModel) -> Unit,
) {
    val context = LocalContext.current
    val oneTapClient = Identity.getSignInClient(context)
    val dialogState = rememberDialogState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
    ) { result ->
        googleAuthViewModel.googleSignInWithIntent(
            oneTapClient = oneTapClient,
            isResultSuccess = result.resultCode == Activity.RESULT_OK,
            resultData = result.data,
        )
        state.closeDialog()


    }

    LaunchedEffect(state.opened) {
        if (state.opened) {
            val intentRequest = googleAuthViewModel.googleSignIn(clientId, oneTapClient)
            if (intentRequest is IntentSenderRequest) {
                launcher.launch(intentRequest)
            } else {
                Toast.makeText(context, intentRequest.toString(), Toast.LENGTH_LONG).show()
                state.closeDialog()
            }
        }


    }

    LaunchedEffect(context) {
        googleAuthViewModel.uiState.collect { uiState ->
            when (uiState) {
                is UiState.Error -> {
                    dialogState.closeDialog()
                    Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
                }

                is UiState.Loading -> {
                    dialogState.openDialog()
                }

                is UiState.Success -> {
                    dialogState.closeDialog()
                    if (uiState.data!!.user?.email != null) {
                        onUserDataReceived(uiState.data)
                    } else {
                        Toast.makeText(
                            context,
                            "Google SignIn Failed. Try Again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                else -> {
                    Toast.makeText(
                        context,
                        "State is None",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    LoadingDialogWidget(
        dialogState = dialogState
    )
}