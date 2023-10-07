package com.compose.app.presentation.category.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.app.navigation.nav_graph.main.MainDestination


@Composable
fun CategoryContentScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
) {
    val list = remember { (1..50).map { it.toString() } }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
    ) {
        itemsIndexed(items = list) { index, it ->
            Card(modifier = modifier
                .padding(bottom = 10.dp, end = 10.dp)
                .height(280.dp).
            clickable {
                navController.navigate(MainDestination.Detail.route)
            }) {
                Column {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier.weight(2f)
                    )
                    Spacer(modifier = modifier.padding(vertical = 5.dp))
                    Row(modifier = modifier.padding(start = 10.dp)) {
                        Icon(
                            imageVector = Icons.Default.Star, contentDescription = null,
                            tint = Color.Yellow
                        )
                        Text("5.0", color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                    Text("Product $it", color = MaterialTheme.colorScheme.onPrimaryContainer, modifier = modifier.padding(start = 10.dp))
                    Spacer(modifier = modifier.padding(vertical = 2.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            append("5.0")
                        }
                        withStyle(style = SpanStyle(fontSize = 12.sp,color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                            append("SAR")
                        }
                    }, modifier = modifier
                        .padding(end = 10.dp)
                        .align(Alignment.End))
                    Spacer(modifier = modifier.padding(vertical = 5.dp))
                    Button(
                        onClick = {},
                        modifier = modifier.align(alignment = Alignment.CenterHorizontally)
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
    }

}