package com.example.sensor_tip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.sensor_tip.ui.theme.SensortipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        Header(balance = fakeWallet.value.balance)
                        CardWaveList(list = fakeWallet.value)
                        TransactionList()
                    }
                }
            }
        }
    }
}