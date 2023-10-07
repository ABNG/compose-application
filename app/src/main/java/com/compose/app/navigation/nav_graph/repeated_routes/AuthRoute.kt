package com.compose.app.navigation.nav_graph.repeated_routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.presentation.auth.screen.AuthScreen

fun NavGraphBuilder.authRoute(){
    composable(route = AuthDestination.Auth.route){
        AuthScreen()
    }
}