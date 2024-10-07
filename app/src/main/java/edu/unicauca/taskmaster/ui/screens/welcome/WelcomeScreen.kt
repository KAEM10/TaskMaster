package edu.unicauca.taskmaster.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles


@Composable
fun WelcomeScreen(blurRadius: Float) {
    // Usar el fondo reutilizable con círculos
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundWithCircles(blurRadius)

        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.icono_task_master),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre de la aplicación
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp
            )

            // Lema o subtítulo
            Text(
                text = stringResource(id = R.string.app_slogan),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(blurRadius = 0f)
}