package com.compose.app.navigation.nav_graph.root

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.presentation.onboard.screen.FirstOnBoardScreen
import com.compose.app.presentation.onboard.screen.SecondOnBoardScreen
import com.compose.app.presentation.onboard.screen.ThirdOnBoardScreen

fun NavGraphBuilder.onBoardNavGraph(navController: NavHostController){
    navigation(startDestination = RootDestination.FirstOnBoard.route, route = Graph.ONBOARD) {
        composable(route = RootDestination.FirstOnBoard.route) {
            FirstOnBoardScreen(navController = navController)
        }
        composable(route = RootDestination.SecondOnBoard.route) {
            SecondOnBoardScreen(navController = navController)
        }
        composable(route = RootDestination.ThirdOnBoard.route) {
            ThirdOnBoardScreen(navController = navController)
        }
    }
}