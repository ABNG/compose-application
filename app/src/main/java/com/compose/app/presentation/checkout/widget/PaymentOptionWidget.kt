package com.compose.app.presentation.checkout.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.app.presentation.checkout.screen.PaymentOptionType
import com.compose.app.presentation.util.NoRippleInteractionSource

@Composable
fun PaymentOptionWidget(
    modifier: Modifier = Modifier,
    selectedPaymentOption: PaymentOptionType,
    onValueChange: (PaymentOptionType) -> Unit
) {
    PaymentOptionType.values().forEach { paymentType ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = modifier
                .fillMaxWidth()
                .toggleable(
                    value = selectedPaymentOption == paymentType,
                    role = Role.RadioButton,
                    onValueChange = { value ->
                        if (value)
                            onValueChange(paymentType)
                    },
                    indication = null,
                    interactionSource = NoRippleInteractionSource(),
                )
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            RadioButton(
                selected = selectedPaymentOption == paymentType,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Text(paymentType.typeName,
                color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PaymentOptionWidgetPreview() {
    PaymentOptionWidget(
        selectedPaymentOption = PaymentOptionType.CASH,
        onValueChange = {}
    )
}