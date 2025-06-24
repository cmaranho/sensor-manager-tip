package com.example.sensor_tip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CardWaveList(
    list: MainWallet
) {
    val lazyState = rememberLazyListState()

    LazyRow(
        state = lazyState,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(16.dp),
    ) {

        items(items = list.wallets, key = { it.id }) { item ->
            WaveCard(
                currentWalletValue = item.balance,
                maxWalletValue = item.limit,
                waveSize = 120.dp,
                waveColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .size(width = 130.dp, height = 120.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.medium
                    )

            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = item.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                        color = MaterialTheme.colorScheme.tertiary,
                    )

                    Text(
                        text = item.balance.formaterReal(),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }
        }
    }
}