package com.compose.app.navigation.nav_graph.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.compose.app.R

sealed class MainDestination(
    val route: String,
    @StringRes val title: Int? = null,
    val icon: ImageVector? = null
) {
    data object Main : MainDestination("main/{user}") {
        val arguments = listOf(
            navArgument("user") {
                type = NavType.StringType
            }
        )

        fun routeWithUserModel(userModel: String) = "main/$userModel"
    }

    data object Home : MainDestination("home", R.string.home, Icons.Default.Home)
    data object Category : MainDestination("category", R.string.category, Icons.Default.Search)
    data object Cart : MainDestination("cart", R.string.cart, Icons.Default.ShoppingCart)
    data object Profile : MainDestination("profile", R.string.profile, Icons.Default.Person)
    data object Detail : MainDestination("detail/{productId}") {
        val arguments = listOf(
            navArgument("productId") {
                type = NavType.IntType
            }
        )

        fun routeWithProductId(productId: Int) = "detail/$productId"
    }

    data object Address : MainDestination("address")
    data object Checkout : MainDestination("checkout/{lat}/{long}") {
        val arguments = listOf(
            navArgument("lat") {
                type = NavType.FloatType
            },
            navArgument("long") {
                type = NavType.FloatType
            }
        )

        fun routeWithLatLong(lat: Float, long: Float) = "checkout/$lat/$long"
    }

    data object Success : MainDestination("success")
    data object UpdateProfile : MainDestination("update_profile")

}
