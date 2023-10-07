package com.compose.app.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.app.navigation.nav_graph.main.MainDestination
import com.compose.app.presentation.home.screen.widget.CarousalSlider
import com.compose.app.presentation.home.screen.widget.CategoryWidget
import com.compose.app.presentation.home.screen.widget.NewArrivedProductList
import com.compose.app.presentation.util.AppPreferencesViewModel
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    spanCount: Int = 2,

) {

    val list = (1..50).map { it.toString() }

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
                    CategoryWidget(modifier = modifier)
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "New Arrived", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(start = 12.dp)
                    )
                    Spacer(modifier = modifier.padding(vertical = 8.dp))
                }
            }
            //modifier = modifier.padding(horizontal = 12.dp)
            items(items = list) { text ->
                NewArrivedProductList(modifier = modifier, text = text) {
                    navController.navigate(MainDestination.Detail.route)
                }
            }


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}