package com.compose.app.presentation.onboard.widget

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun OnBoardWidget(
    @RawRes lottieResourceFile: Int, title: String, @StringRes desc: Int, onNext: () -> Unit,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    showNextButton: Boolean = true,
    onBack: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResourceFile))
    val clipSpecs = LottieClipSpec.Progress(0.1f, 0.5f)
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            reverseOnRepeat = true,
            clipSpec = clipSpecs,
            modifier = modifier.weight(1f),
        )
        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = desc),
            textAlign = TextAlign.Center,
            modifier = modifier.padding(10.dp)
        )
        if (showBackButton && showNextButton) {
            Row {
                ElevatedButton(
                    onClick = onBack,
                ) {
                    Text(text = "Back")
                }
                Spacer(modifier = modifier.width(20.dp))
                ElevatedButton(
                    onClick = onNext,
                ) {
                    Text(text = "Next")
                }
            }
        } else if (showBackButton) {
            ElevatedButton(
                onClick = onBack,
            ) {
                Text(text = "Back")
            }
        } else {
            ElevatedButton(
                onClick = onNext,
                modifier = modifier.width((configuration.screenWidthDp * 0.75).dp)
            ) {
                Text(text = "Next")
            }
        }
        Spacer(modifier = modifier.size(10.dp))
    }
}