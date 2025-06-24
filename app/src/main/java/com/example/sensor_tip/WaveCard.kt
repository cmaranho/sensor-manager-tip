package com.example.sensor_tip

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlin.math.sin


@Composable
fun WaveCard(
    currentWalletValue: Float,
    maxWalletValue: Float,
    waveSize: Dp = 180.dp,
    waveColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val minWaveHeight = 20.dp

    val (_, accelerometer) = rememberDeviceMotion()
    val (accX, accY) = accelerometer

    val fill = (currentWalletValue / maxWalletValue).coerceIn(0f, 1f)

    val targetHeight = lerp(minWaveHeight, waveSize, fill)
    val animatedHeight by animateDpAsState(targetValue = targetHeight, label = "alturaOnda")

    val accelerometerX by animateFloatAsState(
        accX / 4,
        label = "accelerometerX"
    )
    val accelerometerY by animateFloatAsState(
        accY / 4,
        label = "accelerometerY"
    )

    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = modifier
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedHeight)
        ) {
            val width = size.width
            val height = size.height
            val centerY = height / 5

            val path = Path()
            val offsetX = -accelerometerX
            val amplitudeY = height * 0.35f + accelerometerY * 40f

            val waveCycles = 1
            val segmentWidth = size.width / waveCycles

            path.moveTo(0f, centerY)

            repeat(waveCycles) { i ->
                val startX = i * segmentWidth
                val endX = startX + segmentWidth

                val controlX1 = startX + segmentWidth / 3
                val controlY1 = centerY + sin((i + offsetX)) * amplitudeY

                val controlX2 = startX + 2 * segmentWidth / 3
                val controlY2 = centerY - sin((i + offsetX)) * amplitudeY

                path.cubicTo(
                    controlX1, controlY1,
                    controlX2, controlY2,
                    endX, centerY
                )
            }

            path.lineTo(width, height)
            path.lineTo(0f, height)
            path.close()

            drawPath(
                path = path,
                color = waveColor
            )
        }

        content()
    }
}