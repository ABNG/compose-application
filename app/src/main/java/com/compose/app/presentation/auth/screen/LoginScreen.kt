package com.compose.app.presentation.auth.screen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.compose.app.R
import com.compose.app.data.firebase.model.UserModel
import com.compose.app.data.firebase.model.toJson
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.auth.bloc.RegistrationFormValidationEvent
import com.compose.app.presentation.auth.bloc.RegistrationType
import com.compose.app.presentation.auth.bloc.RegistrationViewModel
import com.compose.app.presentation.auth.widget.CustomTextField
import com.compose.app.presentation.auth.widget.google_signin.GoogleSignInWidget
import com.compose.app.presentation.auth.widget.google_signin.rememberGoogleSignInDialogState
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.loading_dialog.LoadingDialogWidget
import com.compose.app.presentation.util.widget.loading_dialog.rememberDialogState

@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val state = viewModel.validationState
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val dialogState = rememberDialogState()
//    viewModel.uiState.collectAsState(initial = ).value
    val googleSignInDialogState = rememberGoogleSignInDialogState()
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
                    if (uiState.data!!.user?.email != null) {
                        navigateToMain(context, navController, uiState.data)
                    } else {
                        Toast.makeText(
                            context,
                            "SignIn Failed. Try Again",
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
                imeAction = ImeAction.Done
            )
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            Button(onClick = {
                viewModel.onEvent(RegistrationFormValidationEvent.Submit(RegistrationType.LOGIN))

            }) {
                Text("Login")
            }
            TextButton(
                onClick = {
                    navController.navigate(AuthDestination.Signup.route)

                }, modifier = modifier
                    .align(Alignment.End)
                    .padding(end = (configuration.screenWidthDp / 3.5).dp)
            ) {
                Text("Sign Up")
            }
            Spacer(modifier = modifier.padding(vertical = 20.dp))
            //convert to px or dp
//        val density = LocalDensity.current.density
//        val px = someDpValue * density
//        val dp = somePxValue / density
            ElevatedButton(onClick = {
                googleSignInDialogState.openDialog()
            }) {
                GoogleSignInWidget(
                    state = googleSignInDialogState,
                    clientId = stringResource(R.string.web_client_id),
                ) {
                    navigateToMain(context, navController, it)
                }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .decoderFactory(SvgDecoder.Factory())
                        .data(R.raw.google_icon)
                        .size(width = 50, height = 50)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = modifier.padding(horizontal = 5.dp))
                Text("Sign in with Google")
            }
        }
    }
}

private fun navigateToMain(
    context: Context,
    navController: NavHostController,
    userModel: UserModel
) {
    Toast.makeText(context, "SignIn Successful", Toast.LENGTH_LONG)
        .show()
    val value = Uri.encode(userModel.toJson())
    navController.navigate(MainDestination.Main.routeWithUserModel(userModel = value)) {
        popUpTo(Graph.AUTH) {
            inclusive = true
        }
    }
}