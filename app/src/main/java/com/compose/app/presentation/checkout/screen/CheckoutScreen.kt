package com.compose.app.presentation.checkout.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.checkout.widget.AddressWidget
import com.compose.app.presentation.checkout.widget.CartWidget
import com.compose.app.presentation.checkout.widget.OrderDetailWidget
import com.compose.app.presentation.checkout.widget.PaymentOptionWidget
import com.compose.app.presentation.checkout.widget.PlaceOrderWidget
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget
import kotlinx.collections.immutable.toImmutableList


enum class PaymentOptionType(val typeName: String) {
    CARD(typeName = "Credit/ Debit Card"), CASH(typeName = "Cash on Delivery")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavHostController, modifier: Modifier = Modifier,
    lat: Double? = null, long: Double? = null,
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    var selectedPaymentOption by remember {
        mutableStateOf(PaymentOptionType.CARD)
    }

    val productList = checkoutViewModel.products

    val subTotal = productList.fold(0) { acc, product ->
        acc + (product.price * product.quantity)
    }.toDouble()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Checkout")
                },
                navigationIcon = {
                    AppBarBackButtonWidget(navController)
                },
            )
        },
        bottomBar = {
            PlaceOrderWidget(productCount = productList.size, total = subTotal) {
                checkoutViewModel.clearProductDB()
                navController.navigate(MainDestination.Success.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = false
                    }
                }
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            if (lat != null && long != null) {
                AddressWidget(modifier = modifier, lat, long)
            }
            Divider(thickness = 12.dp, color = MaterialTheme.colorScheme.background)
            CartWidget(
                modifier = modifier, configuration = configuration,
                productList = productList.toImmutableList()
            )
            Spacer(modifier = modifier.padding(vertical = 10.dp))
            Divider(thickness = 12.dp, color = MaterialTheme.colorScheme.background)
            PaymentOptionWidget(
                modifier = modifier,
                selectedPaymentOption = selectedPaymentOption,
                onValueChange = { paymentType ->
                    selectedPaymentOption = paymentType
                }
            )
            Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.background)
            OrderDetailWidget(modifier = modifier, subTotal = subTotal, total = subTotal)

        }


    }
}