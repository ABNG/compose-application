package com.compose.app.presentation.home.screen.widget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.app.R

@Composable
fun CategoryWidget(modifier:Modifier = Modifier) {
    val categoryList = listOf<Category>(
        Category(name = R.string.fruits, image = R.drawable.fruits),
        Category(name = R.string.juices, image = R.drawable.juices),
        Category(name = R.string.medicine, image = R.drawable.medicine),
        Category(name = R.string.electronics, image = R.drawable.electronics),
        Category(name = R.string.sports, image = R.drawable.sports),
    )
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items = categoryList) { category ->
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(end = 16.dp)


            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    modifier = modifier.padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = category.image),
                        contentDescription = "Category Image",
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                    )
                    Text(
                        text = stringResource(id = category.name),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

        }
    }
}
data class Category(@StringRes val name: Int, @DrawableRes val image: Int)