package com.example.sensor_tip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Header(
    balance: Float,
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, top = 36.dp, end = 16.dp)
    ) {
        Text(
            "Meu saldo",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.alpha(0.5f)
        )
        Text(
            text = balance.formaterReal(),
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.onBackground,

            )

        Text(
            text = "Carteiras",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}