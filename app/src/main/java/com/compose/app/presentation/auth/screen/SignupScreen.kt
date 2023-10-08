package com.compose.app.presentation.auth.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.compose.app.presentation.auth.bloc.RegistrationFormValidationEvent
import com.compose.app.presentation.auth.bloc.RegistrationType
import com.compose.app.presentation.auth.bloc.RegistrationViewModel
import com.compose.app.presentation.auth.widget.AcceptTermWidget
import com.compose.app.presentation.auth.widget.CustomTextField
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.loading_dialog.LoadingDialogWidget
import com.compose.app.presentation.util.widget.loading_dialog.rememberDialogState

@Composable
fun SignupScreen(
    navController: NavHostController, modifier: Modifier = Modifier,
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state = viewModel.validationState
    val context = LocalContext.current
    val dialogState = rememberDialogState()

    LaunchedEffect(context) {
        viewModel.uiState.collect { uiState ->
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
                    if (uiState.data!!.user != null) {
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG)
                            .show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            context,
                            "Registration Failed. Try Again",
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

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(RegistrationFormValidationEvent.NameChanged(it))
                },
                hasError = state.nameErrorResId != null,
                errorMessage = state.nameErrorResId,
                label = "Name",
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            CustomTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegistrationFormValidationEvent.EmailChanged(it))
                },
                hasError = state.emailErrorResId != null,
                errorMessage = state.emailErrorResId,
                label = "Email",
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            CustomTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(RegistrationFormValidationEvent.PasswordChanged(it))
                },
                hasError = state.passwordErrorResId != null,
                errorMessage = state.passwordErrorResId,
                label = "Password",
                keyboardType = KeyboardType.Password,
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            CustomTextField(
                value = state.confirmPassword,
                onValueChange = {
                    viewModel.onEvent(
                        RegistrationFormValidationEvent.ConfirmPasswordChanged(
                            state.password,
                            it
                        )
                    )
                },
                hasError = state.confirmPasswordErrorResId != null,
                errorMessage = state.confirmPasswordErrorResId,
                label = "Confirm Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            AcceptTermWidget(
                value = state.acceptedTerms,
                onValueChange = {
                    viewModel.onEvent(RegistrationFormValidationEvent.AcceptedTermsChanged(it))
                },
                hasError = state.acceptedTermsErrorResId != null,
                errorMessage = state.acceptedTermsErrorResId,
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            Button(onClick = {
                viewModel.onEvent(RegistrationFormValidationEvent.Submit(RegistrationType.SIGN_UP))

            }) {
                Text("Sign Up")
            }
        }
    }
}