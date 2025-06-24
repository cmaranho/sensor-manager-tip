package com.example.sensor_tip

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlin.math.roundToInt


@Composable
fun rememberDeviceMotion(): Pair<Triple<Float, Float, Float>, Triple<Float, Float, Float>> {
    val context = LocalContext.current
    val rotation = remember { mutableStateOf(Triple(0f, 0f, 0f)) }
    val accelerometer = remember { mutableStateOf(Triple(0f, 0f, 0f)) }

    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    val rotationVectorSensor = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    }
    val accelerometerSensor = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    val display = remember(context) { context.display }

    val rotationMatrix = remember { FloatArray(9) }
    val adjustedMatrix = remember { FloatArray(9) }
    val orientation = remember { FloatArray(3) }

    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        val alpha = 0.8f
                        val x = alpha * accelerometer.value.first + (1 - alpha) * event.values[0]
                        val y = alpha * accelerometer.value.second + (1 - alpha) * event.values[1]
                        val z = alpha * accelerometer.value.third + (1 - alpha) * event.values[2]
                        accelerometer.value = Triple(x, y, z)
                    }

                    Sensor.TYPE_ROTATION_VECTOR -> {
                        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)

                        val (axisX, axisY) = when (display.rotation) {
                            Surface.ROTATION_0 -> SensorManager.AXIS_X to SensorManager.AXIS_Z
                            Surface.ROTATION_90 -> SensorManager.AXIS_Z to SensorManager.AXIS_MINUS_X
                            Surface.ROTATION_180 -> SensorManager.AXIS_MINUS_X to SensorManager.AXIS_MINUS_Z
                            Surface.ROTATION_270 -> SensorManager.AXIS_MINUS_Z to SensorManager.AXIS_X
                            else -> SensorManager.AXIS_X to SensorManager.AXIS_Z
                        }
                        SensorManager.remapCoordinateSystem(
                            rotationMatrix,
                            axisX,
                            axisY,
                            adjustedMatrix
                        )
                        SensorManager.getOrientation(adjustedMatrix, orientation)

                        val alpha = 0.9f
                        var rotX = Math.toDegrees(orientation[1].toDouble()).toFloat() * -1
                        var rotY = Math.toDegrees(orientation[2].toDouble()).toFloat() * -1
                        var rotZ = Math.toDegrees(orientation[0].toDouble()).toFloat() * -1

                        rotX = alpha * rotation.value.first + (1 - alpha) * rotX
                        rotY = alpha * rotation.value.second + (1 - alpha) * rotY
                        rotZ = alpha * rotation.value.third + (1 - alpha) * rotZ


                        rotX = (rotX * 10).roundToInt() / 10f
                        rotY = (rotY * 10).roundToInt() / 10f
                        rotZ = (rotZ * 10).roundToInt() / 10f

                        rotation.value = Triple(rotX, rotY, rotZ)
                    }
                }
            }
        }

        rotationVectorSensor?.let {
            sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI)
        }

        accelerometerSensor?.let {
            sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI)
        }

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    val derivedRotation by remember { derivedStateOf { rotation.value } }
    val derivedAccelerometer by remember { derivedStateOf { accelerometer.value } }


    return Pair(derivedRotation, derivedAccelerometer)
}