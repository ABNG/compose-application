package com.compose.app.presentation.address.screen

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.util.permission.PermissionViewModel
import com.compose.app.presentation.util.permission.RequestPermissionDialog
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavHostController, modifier: Modifier = Modifier,
                  viewModel: PermissionViewModel = hiltViewModel()
) {
    if (!viewModel.isPermissionGranted) {
        RequestPermissionDialog(
            permissions = listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    } else {
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 8f)
        }
        val markerState = rememberMarkerState(position = singapore)

        var mapProperties by remember {
            mutableStateOf(
                MapProperties(
                    maxZoomPreference = 10f, minZoomPreference = 5f,
                    isMyLocationEnabled = true
                )
            )
        }
        var mapUiSettings by remember {
            mutableStateOf(
                MapUiSettings(mapToolbarEnabled = false, zoomControlsEnabled = false)
            )
        }

        var infoWindowOpen: Boolean = false
        val coroutineScope = rememberCoroutineScope()

        //https://www.boltuix.com/2022/11/place-marker-on-center-of-screen-on.html

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("Map Screen")
                },
                    navigationIcon = {
                        AppBarBackButtonWidget(navController)
                    },)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Timber.wtf(message = markerState.position.toString())
                    navController.navigate(MainDestination.Checkout.route)
                }) {
                    Text(text = "Confirm Location", modifier = modifier.padding(horizontal = 15.dp))
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            Box(modifier = modifier.fillMaxSize().padding(it)) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    properties = mapProperties,
                    uiSettings = mapUiSettings,
                    modifier = modifier.matchParentSize(),
                    onMapClick = { currentLatLng ->
                        markerState.position = currentLatLng
                        coroutineScope.launch {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLng(
                                    currentLatLng
                                )
                            )
                        }
                    }
                ) {
                    MarkerInfoWindow(
                        state = markerState,
                        title = "Marker in Sydney",
                        draggable = true,
                        onClick = { marker ->
                            if (infoWindowOpen) {
                                marker.hideInfoWindow()
                            } else {
                                marker.showInfoWindow()
                            }
                            infoWindowOpen = !infoWindowOpen
                            false
                        },
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    )
                    { marker ->
                        Box(
                            modifier = modifier
                                .background(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                                ),
                        ) {
                            Column(
                                modifier = modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = modifier.height(24.dp))
                                Text(
                                    text = marker.title ?: "Marker Title",
                                    textAlign = TextAlign.Center,
                                    modifier = modifier
                                        .padding(top = 10.dp)
                                        .fillMaxWidth(),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                Spacer(modifier = modifier.height(8.dp))
                                Text(
                                    text = "Customizing a marker's info window",
                                    textAlign = TextAlign.Center,
                                    modifier = modifier
                                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                        .fillMaxWidth(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                Spacer(modifier = modifier.height(24.dp))
                            }

                        }
                    }
                    Marker(
                        state = MarkerState(position = LatLng(35.66, 139.6)),
                        title = "Marker in Tokyo",

                        )

                }
                Button(onClick = {
                    // Move the camera to a new zoom level
                    cameraPositionState.move(CameraUpdateFactory.newLatLng(LatLng(-34.0, 151.0)))
                }) {
                    Text(text = "Sydney")
                }
            }
        }
    }
}