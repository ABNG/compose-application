package com.compose.app.presentation.main.widget

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.app.navigation.nav_graph.main.MainDestination

@Composable
fun MyBottomBar(navController: NavHostController) {
    val items = listOf(
        MainDestination.Home,
        MainDestination.Category,
        MainDestination.Cart,
        MainDestination.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val showBottomBar = items.any { it.route == currentDestination?.route }
    if (showBottomBar) {
        NavigationBar(
            containerColor = Color.White,
        ) {

            items.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon!!,
                            contentDescription = "Navigation Icon"
                        )
                    },
                    label = { Text(stringResource(screen.title!!)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.switchTabs(screen.route)
                    }
                )
            }
        }
    }
}
fun NavHostController.switchTabs(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}