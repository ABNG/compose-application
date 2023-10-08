package com.compose.app.presentation.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.ErrorStateWidget
import com.compose.app.presentation.util.widget.LoadingStateWidget
import com.compose.app.presentation.util.widget.NoneStateWidget

@Composable
fun DetailScreen(
    navController: NavHostController, modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val productState = detailViewModel.productState
    LaunchedEffect(Unit) {
        if (productState is UiState.None) {
            detailViewModel.getProductById()
        }
    }

    when (productState) {
        is UiState.Error -> {
            ErrorStateWidget(
                modifier = modifier.fillMaxSize(),
                errorMessage = productState.errorMessage
            ) {
                detailViewModel.getProductById()
            }
        }

        is UiState.Loading -> {
            LoadingStateWidget(modifier = modifier)
        }

        is UiState.None -> {
            NoneStateWidget(message = productState.message)
        }

        is UiState.Success -> {
            val productItem = productState.data!!
            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(productItem.images.first())
                        .crossfade(enable = true).build(),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.height(280.dp)
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
                    productItem.title,
                    fontWeight = FontWeight.Bold,
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
                            append(productItem.price.toString())
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
                Text(
                    text = productItem.description,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = modifier.padding(start = 10.dp)
                )
                Spacer(modifier = modifier.padding(vertical = 15.dp))
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth(0.8f)
                ) {
                    Text("Add to Cart")
                }
            }
        }
    }

}