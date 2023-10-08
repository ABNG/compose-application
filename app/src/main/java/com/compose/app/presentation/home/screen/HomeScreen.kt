package com.compose.app.presentation.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.category.screen.CategoryViewModel
import com.compose.app.presentation.home.widget.CarousalSlider
import com.compose.app.presentation.home.widget.CategoryWidget
import com.compose.app.presentation.home.widget.NewArrivedProductList
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget
import com.compose.app.presentation.util.widget.ErrorStateWidget
import com.compose.app.presentation.util.widget.LoadingStateWidget
import com.compose.app.presentation.util.widget.NoneStateWidget
import kotlinx.collections.immutable.toImmutableList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier,
    spanCount: Int = 2,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val categoryState = categoryViewModel.categoryState

    val productState = homeViewModel.productState


    LaunchedEffect(Unit) {
        if (categoryState is UiState.None) {
            categoryViewModel.getAllCategories(10)
        }

        if (productState is UiState.None) {
            homeViewModel.getAllProducts(0, 100)
        }
    }



    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Compose App") },
            navigationIcon = {
                AppBarBackButtonWidget(navController)
            },
        )
    }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
            modifier = modifier
                .padding(it),
        ) {
            item(
                span = {
                    GridItemSpan(spanCount)
                }
            ) {
                Column {
                    CarousalSlider(modifier = modifier)
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Categories", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(start = 12.dp)
                    )
                    Spacer(modifier = modifier.padding(vertical = 8.dp))
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (categoryState) {
                            is UiState.Error -> {
                                ErrorStateWidget(
                                    modifier = modifier,
                                    errorMessage = categoryState.errorMessage
                                ) {
                                    categoryViewModel.getAllCategories(10)
                                }
                            }

                            is UiState.Loading -> {
                                LoadingStateWidget(modifier = modifier)
                            }

                            is UiState.None -> {
                                NoneStateWidget(message = categoryState.message)
                            }

                            is UiState.Success -> {
                                CategoryWidget(
                                    modifier = modifier,
                                    categoryData = categoryState.data!!.toImmutableList()
                                )
                            }
                        }
                    }

                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "New Arrived", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(start = 12.dp)
                    )
                    Spacer(modifier = modifier.padding(vertical = 8.dp))
                }
            }
            //modifier = modifier.padding(horizontal = 12.dp)
            when (productState) {
                is UiState.Error -> {
                    item(span = {
                        GridItemSpan(spanCount)
                    }) {
                        ErrorStateWidget(
                            modifier = modifier,
                            errorMessage = productState.errorMessage
                        ) {
                            homeViewModel.getAllProducts(0, 100)
                        }
                    }
                }

                is UiState.Loading -> {
                    item(span = {
                        GridItemSpan(spanCount)
                    }) {
                        LoadingStateWidget(modifier = modifier)
                    }
                }

                is UiState.None -> {
                    item(span = {
                        GridItemSpan(spanCount)
                    }) {
                        NoneStateWidget(message = productState.message)
                    }
                }

                is UiState.Success -> {
                    items(items = productState.data!!,
                        key = { item ->
                            item.id
                        }) { productItem ->
                        NewArrivedProductList(modifier = modifier, productModelItem = productItem) {
                            navController.navigate(
                                MainDestination.Detail.routeWithProductId(
                                    productItem.id
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}