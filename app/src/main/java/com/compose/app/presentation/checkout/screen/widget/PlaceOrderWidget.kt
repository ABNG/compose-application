package com.compose.app.presentation.checkout.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.app.navigation.nav_graph.main.MainDestination

@Composable
fun PlaceOrderWidget(modifier: Modifier = Modifier,
                     onClick:()->Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("4 items", color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("AED 641.00", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            Spacer(modifier = modifier.padding(top = 15.dp))
            Button(
                modifier = modifier.fillMaxWidth(0.7f),
                onClick = onClick) {
                Text("Place Order", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}