package com.compose.app.presentation.profile.widget

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import timber.log.Timber


@Composable
fun ImagePickerWidget(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    @StringRes imageUriErrorResId: Int?,
    imageUpdate: (Uri) -> Unit
) {

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {

            Timber.wtf(it.path)
            imageUpdate(it)
        } else {
            Timber.wtf("No media selected")
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            //clip(CircleShape) on modifier used only to make indication circleShape
            modifier = modifier.size(80.dp).clip(CircleShape).clickable(
                indication = rememberRipple(
                    bounded = true, color = Color.Green
                ),
                interactionSource = remember { MutableInteractionSource() },
            ) {
                photoPicker.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }.shadow(
                elevation = 5.dp,
                shape = CircleShape,
            ),
            shape = CircleShape,
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {

                AsyncImage(
                    model = if (imageUri == null) Text(
                        "+ ADD", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                    else ImageRequest.Builder(LocalContext.current).data(imageUri)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                )

            }
        }
        if (imageUriErrorResId != null) {
            Text(
                text = stringResource(imageUriErrorResId),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Red
            )
        }
    }

}