package com.compose.app.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.app.R
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.presentation.util.ComposeLifecycleObserver

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewArrivedProductList(modifier: Modifier = Modifier, productModelItem: ProductModelItem, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(6.dp)
            .size(170.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
    ) {

        Column {
            AsyncImage(
                model =  ImageRequest.Builder(LocalContext.current).data(productModelItem.images.first())
                    .crossfade(enable = true).build(),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = modifier.weight(2f)
            )
            Spacer(modifier = modifier.padding(vertical = 2.dp))
            Text(buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    append("SAR")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    append(productModelItem.price.toString())
                }
            }, modifier = modifier.padding(start = 10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text(
                    text = productModelItem.title, fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = modifier.weight(1f)
                )

                Row {
                    Icon(
                        imageVector = Icons.Filled.StarRate,
                        contentDescription = "rating",
                        tint = Color.Yellow
                    )
                    Text(
                        text = "5", fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }


}