package edu.unicauca.taskmaster.ui.screens.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar

@Composable
fun RewardScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            HeaderTask(
                imagen = R.drawable.icono_task_master,
                textoHeader = "Recompensas",
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )

            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                reward(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    tasksCompleted = 17,  // Tareas completadas
                    totalTasks = 20        // Total de tareas
                )
            }

            NavBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun reward(
    modifier: Modifier = Modifier,
    tasksCompleted: Int,  // Tareas completadas
    totalTasks: Int        // Total de tareas
){
    val percentageCompleted = (tasksCompleted.toFloat() / totalTasks) * 100
    // Mostrar recompensa si el porcentaje es mayor o igual al 85%
    if (percentageCompleted >= 85) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Felicidades!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Imagen de recompensa (ej. trofeo o medalla)
            Image(
                painter = painterResource(id = R.drawable.icon_calendar), // Agrega una imagen de trofeo o similar en res/drawable
                contentDescription = "Recompensa",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de recompensa
            Text(
                text = "Has completado el 85% de tus tareas. ¡Sigue así!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 18.sp
                )
            )
        }
    } else {
        // Mensaje si no se ha alcanzado el 85%
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Sigue trabajando!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje motivacional
            Text(
                text = "Has completado el ${percentageCompleted.toInt()}% de tus tareas. ¡Alcanza el 85% para obtener una recompensa!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewRewardScreen() {
    RewardScreen()
}