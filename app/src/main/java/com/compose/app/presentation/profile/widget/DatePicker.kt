package com.compose.app.presentation.profile.widget

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWidget(
    modifier: Modifier = Modifier,
    dateOfBirth: String,
    @StringRes dateOfBirthErrorResId: Int?,
    pickedDate: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Date().time
    )
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePickerDialog = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePickerDialog = false
                    pickedDate(
                        SimpleDateFormat("dd/MM/yyyy").format(
                            datePickerState.selectedDateMillis!!
                        )
                    )
                }) {
                    Text("ok")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePickerDialog = false
                }) {
                    Text("cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                dateValidator = { timestamp ->
                    // Disable all the days before today
                    timestamp >= Date().time
                },
            )
        }
    }
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        value = dateOfBirth,
        readOnly = true,
        onValueChange = {},
        modifier = modifier.onFocusChanged { focusState ->
            showDatePickerDialog = focusState.isFocused
        },
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            showDatePickerDialog = true
                        }
                    }
                }
            },
        label = {
            Text("DOB")
        },
        isError = dateOfBirthErrorResId != null,
        supportingText = {
            if (dateOfBirthErrorResId != null) {
                Text(text = stringResource(dateOfBirthErrorResId))
            }
        }
    )


}