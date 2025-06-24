package com.example.sensor_tip

import java.util.UUID

data class MainWallet(
    val balance : Float = 1000f,
    val wallets: List<Wallet> = emptyList(),
)

data class Wallet(
    val balance: Float = 0f,
    val limit: Float = 0f,
    val title: String = "",
    val id: String = UUID.randomUUID().toString(),
)