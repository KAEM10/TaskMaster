package edu.unicauca.taskmaster.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.theme.*

@Composable
fun BackgroundWithCircles(blurRadius: Float) {
    Box(modifier = Modifier.fillMaxSize().background(background)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Definición de las dimensiones de la pantalla
            val screenWidth = size.width
            val screenHeight = size.height
            var difuminado = blurRadius
            if(blurRadius <= 0){
                difuminado = 1f
            }

            // Dibujar los círculos
            drawCircle(
                color = red.copy(alpha = difuminado),
                radius = screenWidth * (0.5f),
                center = Offset(
                    x = screenWidth * (0.2f),
                    y = screenHeight * (0.12f)
                )
            )

            drawCircle(
                color = red.copy(alpha = difuminado),
                radius = screenWidth * (0.098f),
                center = Offset(
                    x = screenWidth * (0.86f),
                    y = screenHeight * (0.23f)
                )
            )

            drawCircle(
                color = blue.copy(alpha = difuminado),
                radius = screenWidth * (0.048f),
                center = Offset(
                    x = screenWidth * (0.78f),
                    y = screenHeight * (0.36f)
                )
            )

            drawCircle(
                color = blue.copy(alpha = difuminado),
                radius = screenWidth * (0.5f),
                center = Offset(
                    x = screenWidth * (0.8f),
                    y = screenHeight * (0.88f)
                )
            )

            drawCircle(
                color = blue.copy(alpha = difuminado),
                radius = screenWidth * (0.098f),
                center = Offset(
                    x = screenWidth * (0.18f),
                    y = screenHeight * (0.7f)
                )
            )

            drawCircle(
                color = red.copy(alpha = difuminado),
                radius = screenWidth * (0.048f),
                center = Offset(
                    x = screenWidth * (0.098f),
                    y = screenHeight * (0.87f)
                )
            )
        }
    }
}


@Composable
@Preview
fun BackgroundWithCirclesPreview() {
    MaterialTheme {
        //BackgroundWithCircles(blurRadius = 0f)
        BackgroundWithCircles(blurRadius = 0.4f)
    }
}