package com.compose.app.navigation.nav_graph.repeated_routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.main.screen.MainScreen

fun NavGraphBuilder.mainRoute() {
    composable(route = MainDestination.Main.route) {
        MainScreen()
    }
}