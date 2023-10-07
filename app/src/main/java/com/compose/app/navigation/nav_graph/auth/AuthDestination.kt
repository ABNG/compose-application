package com.compose.app.navigation.nav_graph.auth

sealed class AuthDestination(val route: String){
    data object Auth : AuthDestination("auth")
    data object Login : AuthDestination("login")
    data object Signup : AuthDestination("signup")
}
