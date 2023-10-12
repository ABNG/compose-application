package com.compose.app.presentation.cart.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.cart.widget.CartItem
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController, modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartProducts by cartViewModel.products.collectAsStateWithLifecycle()
    var total: Int = 0
    if (cartProducts.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Cart is empty", fontSize = 16.sp, fontWeight = FontWeight.Normal)
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Cart") },
                    navigationIcon = {
                        AppBarBackButtonWidget(navController)
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(MainDestination.Address.route) },
                ) {
                    total = cartProducts.fold(0) { acc, product ->
                        acc + (product.price * product.quantity)
                    }
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
                                append(total.toString())
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
                items(items = cartProducts,
                    key = { product ->
                        product.id
                    }) { product ->
                    CartItem(modifier = modifier, product = product, removeItem = {
                        cartViewModel.deleteProduct(product)
                    }) { quantity ->
                        cartViewModel.updateProductQuantity(quantity, productId = product.id)
                    }
                }
            }
        }
    }
}
