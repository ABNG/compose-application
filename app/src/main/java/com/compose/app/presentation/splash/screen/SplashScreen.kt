package com.compose.app.presentation.splash.screen

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.navigation.nav_graph.Graph
import com.compose.app.navigation.nav_graph.auth.AuthDestination
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.util.AppPreferencesViewModel
import com.compose.app.presentation.util.FirebaseAuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    prefViewModel: AppPreferencesViewModel = hiltViewModel(),
    firebaseAuthViewModel: FirebaseAuthViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    var startAnimation by rememberSaveable {
        mutableStateOf(false)
    }
    val transition = updateTransition(targetState = startAnimation, label = "Text Transition")
    val textTarget by transition.animateDp(label = "text position",
        transitionSpec = {
            tween(
                delayMillis = 250,
                durationMillis = 2000
            )
        }) {
        if (it) 0.dp else (-configuration.screenWidthDp).dp

    }
    val textOpacity by transition.animateFloat(label = "Text Opacity",
        transitionSpec = {
            tween(
                delayMillis = 250,
                durationMillis = 3000
            )
        }) {
        if (it) 1f else 0f
    }
    val textSize by transition.animateInt(label = "Text Size",
        transitionSpec = {
            tween(
                delayMillis = 250,
                durationMillis = 1500
            )
        }) {
        if (it) 36 else 2
    }
//    val textTarget by animateDpAsState(
//        targetValue = if(startAnimation) 0.dp else (-configuration.screenWidthDp).dp,
//        label = "textAnimation",
//        animationSpec = tween(
//            delayMillis = 250,
//            durationMillis = 2000
//        )
//    )

    LaunchedEffect(key1 = Unit) {
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        if (firebaseAuthViewModel.currentSignedInUser().user != null) {
            navController.navigate(MainDestination.Main.route)
        } else if (prefViewModel.read().showOnBoard) {
            navController.navigate(Graph.ONBOARD)
        } else {
            navController.navigate(AuthDestination.Auth.route)
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.offset(textTarget, 0.dp)) {
            Text(
                text = "Splash Screen", fontSize = (textSize).sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.alpha(textOpacity)
            )
        }
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}