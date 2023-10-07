package com.compose.app.navigation.nav_graph.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.compose.app.R

sealed class MainDestination(val route: String, @StringRes val title: Int? = null, val icon: ImageVector? = null) {
    object Main : MainDestination("main")
    object Home : MainDestination("home", R.string.home, Icons.Default.Home)
    object Category : MainDestination("category", R.string.category, Icons.Default.Search)
    object Cart : MainDestination("cart", R.string.cart, Icons.Default.ShoppingCart)
    object Profile : MainDestination("profile", R.string.profile, Icons.Default.Person)
    object Detail : MainDestination("detail")
    object Address : MainDestination("address")
    object Checkout : MainDestination("checkout")
    object Success : MainDestination("success")
    object UpdateProfile : MainDestination("update_profile")

}
