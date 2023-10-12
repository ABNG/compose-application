package com.compose.app.presentation.success.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.compose.app.R
import com.compose.app.navigation.nav_graph.Graph

@Composable
fun SuccessScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.order_placed))
    Surface {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                reverseOnRepeat = true,
                modifier = modifier.height(((configuration.screenHeightDp).dp) * 0.5f) //aspectRatio(16 / 9f)
            )
            Spacer(modifier = modifier.padding(vertical = 10.dp))
            Text(
                "Order placed successfully", fontWeight = FontWeight.Bold, fontSize = 24.sp
            )
            Spacer(modifier = modifier.padding(vertical = 5.dp))
            Button(onClick = {
                navController.popBackStack(
                    destinationId = navController.graph.findStartDestination().id,
                    inclusive = false
                )
            }) {
                Text("Go to home")
            }
        }
    }
}