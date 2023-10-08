package com.compose.app.presentation.category.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.hiltViewModelWithKey
import com.compose.app.presentation.util.widget.ErrorStateWidget
import com.compose.app.presentation.util.widget.LoadingStateWidget
import com.compose.app.presentation.util.widget.NoneStateWidget




@Composable
fun CategoryContentScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    categoryId: Int,
    categoryContentViewModel: CategoryContentViewModel = hiltViewModelWithKey(key = categoryId.toString())
) {
    val productState = categoryContentViewModel.productState
    LaunchedEffect(Unit) {
        if (productState is UiState.None) {
            categoryContentViewModel.getAllProductsByCategoryId(categoryId, 0, 100)
        }
    }
    when (productState) {
        is UiState.Error -> {
            ErrorStateWidget(
                modifier = modifier,
                errorMessage = productState.errorMessage
            ) {
                categoryContentViewModel.getAllProductsByCategoryId(categoryId, 0, 100)
            }
        }

        is UiState.Loading -> {
            LoadingStateWidget(modifier = modifier)
        }

        is UiState.None -> {
            NoneStateWidget(message = productState.message)
        }

        is UiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
            ) {
                items(items = productState.data!!) { product ->
                    Card(modifier = modifier
                        .padding(bottom = 10.dp, end = 10.dp)
                        .height(280.dp)
                        .clickable {
                            navController.navigate(
                                MainDestination.Detail.routeWithProductId(
                                    product.id
                                )
                            )
                        }) {
                        Column {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(product.images.first())
                                    .crossfade(enable = true).build(),
                                contentDescription = "Product Image",
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
                            Text(
                                product.title,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = modifier.padding(start = 10.dp)
                            )
                            Spacer(modifier = modifier.padding(vertical = 2.dp))
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    ) {
                                        append(product.price.toString())
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    ) {
                                        append("SAR")
                                    }
                                }, modifier = modifier
                                    .padding(end = 10.dp)
                                    .align(Alignment.End)
                            )
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
    }

}