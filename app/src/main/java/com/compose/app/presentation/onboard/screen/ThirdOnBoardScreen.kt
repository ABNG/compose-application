package com.compose.app.presentation.onboard.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.R
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.presentation.onboard.screen.widget.OnBoardWidget


@Composable
fun ThirdOnBoardScreen(navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AuthDestination.Auth.route) {
                    popUpTo(Graph.ONBOARD) {
                        inclusive = true
                    }
                }
            }) {
                Icon(imageVector = Icons.Default.NavigateNext, contentDescription = "next")
            }
        }
    ) {
        OnBoardWidget(
            lottieResourceFile = R.raw.onboard3,
            title = "Title OnBoard 3",
            desc = R.string.onboard1_description,
            showNextButton = false,
            onNext = { },
            onBack = { navController.popBackStack() },
            modifier = Modifier.padding(it)
        )
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ThirdOnBoardScreenPreview() {
    ThirdOnBoardScreen(navController = rememberNavController())
}