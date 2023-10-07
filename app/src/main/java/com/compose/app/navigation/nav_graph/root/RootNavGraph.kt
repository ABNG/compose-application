package com.compose.app.navigation.nav_graph.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.navigation.nav_graph.repeated_routes.authRoute
import com.compose.app.navigation.nav_graph.repeated_routes.mainRoute
import com.compose.app.presentation.auth.screen.AuthScreen
import com.compose.app.presentation.main.screen.MainScreen
import com.compose.app.presentation.splash.screen.SplashScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = RootDestination.Splash.route,
        route = Graph.ROOT
    ) {
        composable(route = RootDestination.Splash.route) {
            SplashScreen(navController)
        }
        onBoardNavGraph(navController = navController)
        authRoute()
        mainRoute()
    }
}