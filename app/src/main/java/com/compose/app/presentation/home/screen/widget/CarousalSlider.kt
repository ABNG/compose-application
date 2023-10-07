package com.compose.app.presentation.home.screen.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.compose.app.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarousalSlider(modifier: Modifier = Modifier) {
    val banners = remember {
        listOf(
            R.drawable.banner_2,
            R.drawable.banner_3,
            R.drawable.banner_4,
        )
    }
    val pageState = rememberPagerState(pageCount = {
        banners.size
    })
    LaunchedEffect(Unit) {
        while(true) {
            delay(3000L)
            if (pageState.currentPage == banners.size - 1) {
                pageState.animateScrollToPage(0)
            } else {
                pageState.animateScrollToPage(pageState.currentPage + 1)
            }
        }
    }

    Box(modifier = modifier.height(180.dp)) {
        HorizontalPager(
            state = pageState,
            key = { key -> banners[key] },
            contentPadding = PaddingValues(horizontal = 12.dp),
            pageSpacing = 16.dp

        ) { index ->
            Image(
                painter = painterResource(id = banners[index]),
                contentDescription = "Banner Image",
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = modifier
                .offset(y = -(10).dp)
                .align(Alignment.BottomCenter)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(banners.size) { index ->
                    Box(
                        modifier = modifier
                            .then(
                                if (pageState.currentPage == index) {
                                    modifier.size(8.dp)
                                } else {
                                    modifier.size(5.dp)
                                }
                            )
                            .clip(CircleShape)
                            .background(color = Color.White)

                    )
                }
            }
        }
    }
}