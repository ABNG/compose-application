package com.compose.app.presentation.auth.widget

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp


@Composable
fun AcceptTermWidget(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    hasError: Boolean,
    @StringRes errorMessage: Int?,
) {
    Column {
        Row(
            modifier = modifier.toggleable(
                value = value,
                onValueChange = onValueChange,
                role = Role.Checkbox
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = value,
                onCheckedChange = null
            )
            Spacer(modifier = modifier.padding(horizontal = 6.dp))
            Text(
                "Accept Terms & Condition"
            )
        }
        if (hasError)
            Text(text = stringResource(errorMessage!!), color = Color.Red)
    }
}