package com.compose.app.presentation.util.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AppBarBackButtonWidget(navController: NavHostController) {


    if (navController.previousBackStackEntry != null) {
        IconButton(onClick = { navController.popBackStack() }) {

            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
        }
    } else {
        null
    }


}

