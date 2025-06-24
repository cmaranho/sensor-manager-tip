package com.example.sensor_tip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TransactionList() {
    LazyColumn(
        modifier =
            Modifier
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,

                    )
                .padding(16.dp)
    ) {
        items(count = 4) { i ->
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    headlineColor = MaterialTheme.colorScheme.onSurface,
                    leadingIconColor = MaterialTheme.colorScheme.onSurface,
                    overlineColor = MaterialTheme.colorScheme.onSurface,
                    trailingIconColor = MaterialTheme.colorScheme.onSurface,
                ),
                supportingContent = {
                    Row {
                        Text(
                            "- ",
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                            color = MaterialTheme.colorScheme.error,
                        )
                        Text(
                            "Alimentação : R$${(i + 1) * 10},00"
                        )
                    }
                },
                trailingContent = {
                    Text(
                        "23/06/2025"
                    )
                },
                headlineContent = {
                    Text(
                        "Compra efetuada"
                    )
                },
                modifier = Modifier
                    .fillParentMaxWidth()
            )
        }
    }
}