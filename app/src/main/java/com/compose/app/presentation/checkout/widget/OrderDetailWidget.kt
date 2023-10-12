package com.compose.app.presentation.checkout.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OrderDetailWidget(modifier: Modifier = Modifier, subTotal: Double, total: Double) {
    Surface(
        modifier = modifier
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .clip(RoundedCornerShape(6.dp))
                .background(color = MaterialTheme.colorScheme.inverseSurface)
                .padding(10.dp)
        ) {
            Text(
                "Order Details", fontSize = 14.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Sub total",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
                Text(
                    "SAR $subTotal",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Shipping fee",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
                Text("FREE", fontSize = 13.sp, color = MaterialTheme.colorScheme.inverseOnSurface)
            }
            Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.inverseOnSurface
                            )
                        ) {
                            append("Total")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.inverseOnSurface
                            )
                        ) {
                            append(" Incl. VAT")
                        }
                    },
                )
                Text(
                    "SAR $total",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}