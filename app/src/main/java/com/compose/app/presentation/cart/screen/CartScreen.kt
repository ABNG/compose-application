package com.compose.app.presentation.cart.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.R
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val cartList = (1..50).map { it.toString() }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Cart") },
                navigationIcon = {
                    AppBarBackButtonWidget(navController)
                },)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(MainDestination.Address.route) },
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            append("Checkout")
                        }
                        append("  |  ")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            append("209")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            append("SAR")
                        }
                    },
                    modifier = modifier.padding(horizontal = 10.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(items = cartList) {
                CartItem(modifier = modifier)
            }
        }
    }
}

@Composable
fun CartItem(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.TopEnd) {
        Card(
            modifier = modifier
                .height(150.dp)
                .padding(10.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gaming_laptop),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(2f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Product 1", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
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
                        withStyle(
                            style = SpanStyle(
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        ) {
                            append("SAR")
                        }
                    })
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star, contentDescription = null,
                            tint = Color.Yellow
                        )
                        Text("5.0", color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Remove button"
                        )
                    }
                    Text(
                        text = "1",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier.padding(horizontal = 10.dp)
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .size(30.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add button")
                    }
                }
            }
        }
        IconButton(onClick = { /*TODO*/ },
            modifier.offset {
                IntOffset(x = 20, y = -25)
            }) {
            Icon(imageVector = Icons.Outlined.Clear, contentDescription = null,
                tint = Color.Red)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController())
}