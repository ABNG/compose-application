package com.compose.app.presentation.profile.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.compose.app.presentation.profile.bloc.UpdateProfileFormEvent
import com.compose.app.presentation.profile.bloc.UpdateProfileViewModel
import com.compose.app.presentation.profile.widget.DatePickerWidget
import com.compose.app.presentation.profile.widget.ImagePickerWidget
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget
import com.compose.app.presentation.util.NoRippleInteractionSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    profileViewModel: UpdateProfileViewModel = hiltViewModel()
) {

    //try RelocationRequester  https://www.youtube.com/watch?v=8waTylS0wUc

    val focusManager = LocalFocusManager.current
    val state = profileViewModel.state

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Edit Profile")
            },
            navigationIcon = {
                AppBarBackButtonWidget(navController)
            },
        )
    }, modifier = modifier.clickable(
        indication = null,
        interactionSource = NoRippleInteractionSource(),
    ) {
        focusManager.clearFocus()
    }) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ImagePickerWidget(
                modifier = modifier, imageUri = state.imageUri,
                imageUriErrorResId = state.imageUriErrorResId
            ) { image ->
                profileViewModel.onEvent(UpdateProfileFormEvent.UpdateImage(image))
            }

            Spacer(modifier = modifier.padding(20.dp))

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                value = state.name,
                onValueChange = { name ->
                    profileViewModel.onEvent(UpdateProfileFormEvent.UpdateName(name))
                },
                modifier = modifier,
                label = {
                    Text("Name")
                },
                isError = state.nameErrorResId != null,
                supportingText = {
                    if (state.nameErrorResId != null) {
                        Text(stringResource(state.nameErrorResId))
                    }
                }
            )

            Spacer(modifier = modifier.padding(5.dp))

            DatePickerWidget(
                modifier = modifier, dateOfBirth = state.dateOfBirth,
                dateOfBirthErrorResId = state.dateOfBirthErrorResId
            ) { date ->
                profileViewModel.onEvent(UpdateProfileFormEvent.UpdateDOB(date))
            }


            Spacer(modifier = modifier.padding(5.dp))

            OutlinedTextField(
                value = state.phoneNumber,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
                ),
                onValueChange = { number ->
                    profileViewModel.onEvent(UpdateProfileFormEvent.UpdatePhoneNumber(number))
                },
                modifier = modifier,
                label = {
                    Text("Phone Number")
                },
                isError = state.phoneNumberErrorResId != null,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                supportingText = {
                    if (state.phoneNumberErrorResId != null) {
                        Text(stringResource(state.phoneNumberErrorResId))
                    }
                }
            )

            Spacer(modifier = modifier.padding(20.dp))
            Button(
                onClick = {
                    profileViewModel.onEvent(UpdateProfileFormEvent.Submit)
                }, modifier = modifier.fillMaxWidth(0.7f)
            ) {
                Text("Update")
            }

        }
    }
}