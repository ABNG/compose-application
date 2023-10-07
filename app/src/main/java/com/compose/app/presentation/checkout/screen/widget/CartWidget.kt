package com.compose.app.presentation.checkout.screen.widget

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.app.R

@Composable
fun CartWidget(modifier: Modifier = Modifier,configuration: Configuration) {
    Surface(color = MaterialTheme.colorScheme.primaryContainer) {
        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        append("Shipment")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 11.sp)) {
                        append(" (4 items)")
                    }
                },

                modifier = modifier.padding(start = 10.dp, bottom = 15.dp, top = 10.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(start = 10.dp),
            ) {
                items(14) {
                    Row(
                        modifier = modifier
                            .width(((configuration.screenWidthDp).dp) * 0.5f)
                            .height(80.dp)
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.gaming_laptop),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier.weight(1f)
                        )
                        Column(
                            modifier = modifier.weight(2f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                "Brand",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Normal,
                            )
                            Text(
                                "Marlin Mash Running shoes",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text("AED 109.00", fontSize = 13.sp, fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                }
            }
        }
    }
}

