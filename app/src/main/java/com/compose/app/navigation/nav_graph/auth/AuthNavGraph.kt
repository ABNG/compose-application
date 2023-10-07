package com.compose.app.navigation.nav_graph.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.navigation.nav_graph.repeated_routes.mainRoute
import com.compose.app.presentation.auth.screen.AuthScreen
import com.compose.app.presentation.auth.screen.LoginScreen
import com.compose.app.presentation.auth.screen.SignupScreen
import com.compose.app.presentation.main.screen.MainScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthDestination.Login.route,
        route = Graph.AUTH
    ) {
        composable(route = AuthDestination.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = AuthDestination.Signup.route) {
            SignupScreen(navController = navController)
        }
        mainRoute()
    }

}