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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.app.R
import com.compose.app.navigation.nav_graph.Graph

@Composable
fun SuccessScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current

    Surface {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.android_developer_mode),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.height(((configuration.screenHeightDp).dp) * 0.5f) //aspectRatio(16 / 9f)
            )
            Spacer(modifier = modifier.padding(vertical = 10.dp))
            Text(
                "Order placed successfully", fontWeight = FontWeight.Bold, fontSize = 24.sp
            )
            Spacer(modifier = modifier.padding(vertical = 5.dp))
            Button(onClick = {
                navController.navigate(Graph.MAIN) {
                    popUpTo(Graph.MAIN) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }) {
                Text("Go to home")
            }
        }
    }
}