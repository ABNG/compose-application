package com.compose.app.presentation.onboard.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.R
import com.compose.app.navigation.nav_graph.root.RootDestination
import com.compose.app.presentation.onboard.widget.OnBoardWidget


@Composable
fun SecondOnBoardScreen(navController: NavHostController) {
    Scaffold {
        OnBoardWidget(
            lottieResourceFile = R.raw.onboard2,
            title = "Title OnBoard 2",
            desc = R.string.onboard1_description,
            onNext = { navController.navigate(RootDestination.ThirdOnBoard.route) },
            onBack = { navController.popBackStack() },
            modifier = Modifier.padding(it))
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SecondOnBoardScreenPreview() {
    SecondOnBoardScreen(navController = rememberNavController())
}