package com.example.sensor_tip

import java.text.NumberFormat
import java.util.Locale

fun Float.formaterReal(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}