package com.compose.app.presentation.auth.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.compose.app.navigation.nav_graph.auth.AuthNavGraph

@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    AuthNavGraph(navController = navController)
}