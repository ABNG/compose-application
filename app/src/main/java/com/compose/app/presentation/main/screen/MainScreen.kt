package com.compose.app.presentation.main.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.navigation.nav_graph.main.MainNavGraph
import com.compose.app.presentation.main.screen.widget.MyBottomBar
import com.compose.app.presentation.util.AppPreferencesViewModel

@Composable
fun MainScreen(navController: NavHostController = rememberNavController(),
               prefViewModel: AppPreferencesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if(prefViewModel.read().showOnBoard) {
            prefViewModel.write {
                it.copy(showOnBoard = false)
            }
        }
    }
    Scaffold(bottomBar = {
        MyBottomBar(navController = navController)
    }) {

        MainNavGraph(navController, modifier = Modifier.padding(it))
    }
}