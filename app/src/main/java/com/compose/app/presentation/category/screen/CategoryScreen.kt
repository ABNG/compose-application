package com.compose.app.presentation.category.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.compose.app.presentation.category.view.CategoryContentScreen
import com.compose.app.presentation.util.NoRippleInteractionSource
import com.compose.app.presentation.util.UiState
import com.compose.app.presentation.util.widget.AppBarBackButtonWidget
import com.compose.app.presentation.util.widget.ErrorStateWidget
import com.compose.app.presentation.util.widget.LoadingStateWidget
import com.compose.app.presentation.util.widget.NoneStateWidget
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
    modifier: Modifier = Modifier
) {

    when (val categoryState = categoryViewModel.categoryState) {
        is UiState.Error -> {
            ErrorStateWidget(
                modifier = modifier,
                errorMessage = categoryState.errorMessage
            ) {
                categoryViewModel.getAllCategories(5)
            }
        }

        is UiState.Loading -> {
            LoadingStateWidget(modifier = modifier)
        }

        is UiState.None -> {
            NoneStateWidget(
                message = categoryState.message
            )
        }

        is UiState.Success -> {
            val selectedIndex = remember {
                categoryViewModel.selectedCategoryIndex
            }
            val tabs = categoryState.data!!
            val scrollBehavior =
                TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

            val pagerState = rememberPagerState(
                pageCount = {
                    tabs.size
                }
            )
            val coroutineScope = rememberCoroutineScope()
            val primaryContainerColor: Color = MaterialTheme.colorScheme.onPrimaryContainer

            LaunchedEffect(selectedIndex) {
                if (selectedIndex != -1) {
                    pagerState.animateScrollToPage(selectedIndex)
                    categoryViewModel.selectedCategoryIndex = -1
                }
            }


            Scaffold(
                modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    Column {
                        TopAppBar(
                            title = {
                                Text("Category")
                            },
                            scrollBehavior = scrollBehavior,
                            navigationIcon = {
                                AppBarBackButtonWidget(navController)
                            },
                        )
//                CenterAlignedTopAppBar(title = {
//                    Text("Category")
//                })

                        ScrollableTabRow(
                            selectedTabIndex = pagerState.currentPage,
                            edgePadding = 0.dp,
                            indicator = {
                                Box(
                                    modifier = modifier
                                        .tabIndicatorOffset(it[pagerState.currentPage])
                                        .fillMaxSize()
                                        .padding(vertical = 5.dp)
                                        .drawBehind {
                                            drawRoundRect(
                                                color = primaryContainerColor.copy(alpha = 0.2f),
                                                cornerRadius = CornerRadius(10.dp.toPx())
                                            )
                                        }
                                )
                            }
                        ) {
                            tabs.forEachIndexed { index, categoryItem ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                    selectedContentColor = Color.Black,
                                    unselectedContentColor = MaterialTheme.colorScheme.primary,
                                    interactionSource = NoRippleInteractionSource(),
                                    text = {
                                        Text(
                                            text = categoryItem.name,
                                        )
                                    },
//                            icon = {
//                                Icon(
//                                    imageVector = Icons.Default.ShoppingCart,
//                                    contentDescription = null
//                                )
//                            }
                                )
                            }
                        }
                    }
                },

                ) {

                HorizontalPager(state = pagerState, modifier = modifier.padding(it)) { page ->
                    // Our page content
                    CategoryContentScreen(
                        navController = navController,
                        modifier = modifier,
                        categoryId = tabs[page].id
                    )
                }

            }
        }
    }


}