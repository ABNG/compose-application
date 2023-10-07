package com.compose.app.navigation.nav_graph.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.repeated_routes.authRoute
import com.compose.app.presentation.address.screen.AddressScreen
import com.compose.app.presentation.cart.screen.CartScreen
import com.compose.app.presentation.category.screen.CategoryScreen
import com.compose.app.presentation.checkout.screen.CheckoutScreen
import com.compose.app.presentation.detail.screen.DetailScreen
import com.compose.app.presentation.home.screen.HomeScreen
import com.compose.app.presentation.profile.screen.ProfileScreen
import com.compose.app.presentation.profile.screen.UpdateProfileScreen
import com.compose.app.presentation.success.screen.SuccessScreen

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        composable(route = MainDestination.Home.route){
            HomeScreen(navController)
        }
        composable(route = MainDestination.Category.route){
            CategoryScreen(navController)
        }
        composable(route = MainDestination.Cart.route){
            CartScreen(navController)
        }
        composable(route = MainDestination.Profile.route){
            ProfileScreen(navController)
        }
        composable(route = MainDestination.Detail.route){
            DetailScreen(navController)
        }
        composable(route = MainDestination.Address.route){
            AddressScreen(navController)
        }
        composable(route = MainDestination.Checkout.route){
            CheckoutScreen(navController)
        }
        composable(route = MainDestination.Success.route){
            SuccessScreen(navController)
        }
        composable(route = MainDestination.UpdateProfile.route){
            UpdateProfileScreen(navController)
        }
        authRoute()
    }
}