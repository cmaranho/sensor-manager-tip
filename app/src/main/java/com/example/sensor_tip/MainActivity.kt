package com.example.sensor_tip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.sensor_tip.ui.theme.SensortipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val lazyState = rememberLazyListState()
            val fakeWallet = remember {
                mutableStateOf(
                    MainWallet(
                        balance = 3500f,
                        wallets = listOf(
                            Wallet(
                                balance = 900f,
                                limit = 1000f,
                                title = "🍔 Alimentação"
                            ),
                            Wallet(
                                balance = 365f,
                                limit = 400f,
                                title = "📺 Assinaturas"
                            ),
                            Wallet(
                                balance = 800f,
                                limit = 1500f,
                                title = "💳 Cartão de Crédito"
                            ),
                            Wallet(
                                balance = 256f,
                                limit = 600f,
                                title = "📊 Investimentos"
                            )
                        )
                    )
                )
            }

            SensortipTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {


                        Column(
                            modifier = Modifier.padding(start = 16.dp, top = 36.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            Text(
                                "Meu saldo",
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Left,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.alpha(0.5f)
                            )
                            Text(
                                text = formaterReal(fakeWallet.value.balance),
                                fontSize = 26.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Left,
                                color = MaterialTheme.colorScheme.onBackground,

                                )
                        }

                        Text(
                            text = "Carteiras",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )

                        LazyRow(
                            state = lazyState,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            contentPadding = PaddingValues(16.dp),
                        ) {

                            items(items = fakeWallet.value.wallets, key = { it.id }) { item ->
                                WaveCard(
                                    title = item.title,
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
                                            text = formaterReal(item.balance),
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
                                        Row{
                                            Text(
                                                "- ",
                                                fontSize = 14.sp,
                                                style = MaterialTheme.typography.bodyLarge,
                                                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                                                color = MaterialTheme.colorScheme.error,
                                            )
                                            Text(
                                                "Alimentação : R$${(i+1) * 10},00"
                                            )
                                        }
                                    },
                                    trailingContent = {
                                        Text(
                                            "23/06/2025"
                                        )
                                    },
                                    headlineContent = { Text(
                                        "Compra efetuada"
                                    ) },
                                    modifier = Modifier
                                        .fillParentMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}