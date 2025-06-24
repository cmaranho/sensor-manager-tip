package com.example.sensor_tip

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CardExample(modifier: Modifier = Modifier) {
    val (rotation, accelerometer) = rememberDeviceMotion()

    val (rotX, rotY) = rotation
    val (accX, accY) = accelerometer

    val scale = 0.89f
    val width = 300f;
    val height = 400f;
    val shadowHeight = 400f - scale;
    val shadowWidth = 300f - scale;

    val maxTilt = 35f
    val springStiffness = Spring.StiffnessLow

    val parallaxX by animateFloatAsState(
        targetValue = rotX.coerceIn(-maxTilt, maxTilt),
        animationSpec = spring(stiffness = springStiffness),
        label = "parallaxX"
    )
    val parallaxY by animateFloatAsState(
        targetValue = rotY.coerceIn(-maxTilt, maxTilt),
        animationSpec = spring(stiffness = springStiffness),
        label = "parallaxY"
    )



    Box(modifier = modifier) {

        // SOMBRA
        Image(
            painter = painterResource(R.drawable.example_01),
            contentDescription = "Blurred Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = shadowWidth.dp, height = shadowHeight.dp)
                .graphicsLayer {
                    translationX = -parallaxX * 1.5f
                    translationY = parallaxY * 2f
                    cameraDistance = 12f * density

                }
                .blur(
                    radius = 24.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )


        )

        //IMAGEM
        Image(
            painter = painterResource(R.drawable.example_01),
            contentDescription = "Foreground Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = width.dp, height = height.dp)
                .graphicsLayer {
                    translationX = parallaxX
                    translationY = -parallaxY
                    cameraDistance = 12f * density

                }
                .clip(RoundedCornerShape(16.dp))

        )

        //BORDA
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationX = parallaxX * 0.9f
                    translationY = -parallaxY * 0.9f
                    cameraDistance = 12f * density
                }
                .size(width = width.dp, height = height.dp)
                .background(
                    color = Color.White.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp)
                ),
        )



        Text("X: %.2f | Y: %.2f".format(parallaxX, parallaxY))

    }
}
