package com.compose.app.presentation.util.permission


import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RequestPermissionDialog(
    permissions: List<String>,
    onDismissRequest: () -> Unit = {}, //optional permission
    viewModel: PermissionViewModel = hiltViewModel()
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(
        permissions
    )


    val context = LocalContext.current
    LaunchedEffect(context) {
        multiplePermissionsState.launchMultiplePermissionRequest()
    }

    if (multiplePermissionsState.allPermissionsGranted) {
        viewModel.isPermissionGranted = true
    } else {
        AlertDialog(
            title = {
                Text("Request permissions")
            },
            text = {
                Column {
                    Text(
                        getTextToShowGivenPermissions(
                            multiplePermissionsState.revokedPermissions,
                            multiplePermissionsState.shouldShowRationale
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        if (!multiplePermissionsState.shouldShowRationale) {
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.packageName, null)
                            )
                            context.startActivity(intent)
                        } else {
                            multiplePermissionsState.launchMultiplePermissionRequest()
                        }
                    }) {
                        Text(if (!multiplePermissionsState.shouldShowRationale) "Open Settings" else "Request permissions")
                    }
                }
            },
            onDismissRequest = onDismissRequest,
             confirmButton = {  },
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun getTextToShowGivenPermissions(
    permissions: List<PermissionState>,
    shouldShowRationale: Boolean
): String {
    val revokedPermissionsSize = permissions.size
    if (revokedPermissionsSize == 0) return ""

    val textToShow = StringBuilder().apply {
        append("The ")
    }

    for (i in permissions.indices) {
        textToShow.append(permissions[i].permission)
        when {
            revokedPermissionsSize > 1 && i == revokedPermissionsSize - 2 -> {
                textToShow.append(", and ")
            }

            i == revokedPermissionsSize - 1 -> {
                textToShow.append(" ")
            }

            else -> {
                textToShow.append(", ")
            }
        }
    }
    textToShow.append(if (revokedPermissionsSize == 1) "permission is" else "permissions are")
    textToShow.append(
        if (shouldShowRationale) {
            " important. Please grant all of them for the app to function properly."
        } else {
            " denied. The app cannot function without them."
        }
    )
    return textToShow.toString()
}
