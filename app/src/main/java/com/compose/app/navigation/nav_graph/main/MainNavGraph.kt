package com.compose.app.navigation.nav_graph.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.app.data.firebase.model.LocalUserModel
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.repeated_routes.authRoute
import com.compose.app.presentation.address.screen.AddressScreen
import com.compose.app.presentation.cart.screen.CartScreen
import com.compose.app.presentation.category.screen.CategoryScreen
import com.compose.app.presentation.category.screen.CategoryViewModel
import com.compose.app.presentation.checkout.screen.CheckoutScreen
import com.compose.app.presentation.detail.screen.DetailScreen
import com.compose.app.presentation.home.screen.HomeScreen
import com.compose.app.presentation.profile.screen.ProfileScreen
import com.compose.app.presentation.profile.screen.UpdateProfileScreen
import com.compose.app.presentation.success.screen.SuccessScreen
import com.compose.app.presentation.util.UserViewModel

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        composable(route = MainDestination.Home.route) { entry ->
            val categoryViewModel = entry.sharedViewModel<CategoryViewModel>(navController)
            HomeScreen(navController, categoryViewModel)
        }
        composable(route = MainDestination.Category.route) { entry ->
            val categoryViewModel = entry.sharedViewModel<CategoryViewModel>(navController)
            CategoryScreen(navController, categoryViewModel)
        }
        composable(route = MainDestination.Cart.route) {
            CartScreen(navController)
        }
        composable(route = MainDestination.Profile.route) { entry ->
            val userViewModel = entry.sharedViewModel<UserViewModel>(navController)
            if (userViewModel.userModel == null) {
                userViewModel.initUserModel(LocalUserModel.current)
            }
            ProfileScreen(userViewModel = userViewModel, navController)
        }
        composable(
            route = MainDestination.Detail.route,
            arguments = MainDestination.Detail.arguments
        ) {
            DetailScreen(navController)
        }
        composable(route = MainDestination.Address.route) {
            AddressScreen(navController)
        }
        composable(route = MainDestination.Checkout.route,
            arguments = MainDestination.Checkout.arguments) {
            val lat = it.arguments?.getFloat("lat")
            val long = it.arguments?.getFloat("long")
            CheckoutScreen(navController, lat = lat?.toDouble(), long = long?.toDouble())
        }
        composable(route = MainDestination.Success.route) {
            SuccessScreen(navController)
        }
        composable(route = MainDestination.UpdateProfile.route) { entry ->
            val userViewModel = entry.sharedViewModel<UserViewModel>(navController)
            UpdateProfileScreen(userViewModel = userViewModel, navController)
        }
        authRoute()
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}