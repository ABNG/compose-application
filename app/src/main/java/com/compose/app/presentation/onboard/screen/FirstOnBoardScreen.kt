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
fun FirstOnBoardScreen(navController: NavHostController) {
    Scaffold {
        OnBoardWidget(
            lottieResourceFile = R.raw.onboard1,
            title = "Title OnBoard 1",
            desc = R.string.onboard1_description,
            onNext = { navController.navigate(RootDestination.SecondOnBoard.route) },
            onBack = {},
            showBackButton = false,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun FirstOnBoardScreenPreview() {
    FirstOnBoardScreen(navController = rememberNavController())
}