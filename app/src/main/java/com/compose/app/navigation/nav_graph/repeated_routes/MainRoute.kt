package com.compose.app.navigation.nav_graph.repeated_routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.compose.app.data.firebase.model.UserModel
import com.compose.app.data.firebase.model.fromJson
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.main.screen.MainScreen
import com.google.gson.Gson

fun NavGraphBuilder.mainRoute() {
    composable(
        route = MainDestination.Main.route,
        arguments = MainDestination.Main.arguments
    ) {
        val userModel = it.arguments?.getString("user")?.let { json ->
            json.fromJson()
        }
        MainScreen(userModel = userModel!!)
    }
}