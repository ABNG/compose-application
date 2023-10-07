package com.compose.app.navigation.nav_graph.root

sealed class RootDestination(val route: String){
    data object Splash : RootDestination("splash")
    data object FirstOnBoard : RootDestination("first_onboard")
    data object SecondOnBoard : RootDestination("second_onboard")
    data object ThirdOnBoard : RootDestination("third_onboard")
}