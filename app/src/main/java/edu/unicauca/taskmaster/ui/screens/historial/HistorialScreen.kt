package edu.unicauca.taskmaster.ui.screens.historial

import android.widget.CalendarView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import java.util.*

@Composable
fun HistotialScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier.fillMaxSize(),
            // verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderTask(
                imagen = R.drawable.icono_task_master,
                textoHeader = "Historial",
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
                CalendarWithTasks()
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
fun CalendarWithTasks() {
    // Estado para la fecha seleccionada
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    // Estado para controlar si la fecha ha sido seleccionada manualmente
    var isDateSelectedManually by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // CalendarView (de la vista tradicional)
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 16.dp),
            factory = { context ->
                CalendarView(context).apply {
                    // Ajustar la fecha inicial a la fecha actual
                    date = selectedDate.timeInMillis

                    // Listener para la selección de una nueva fecha
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        // Actualiza la fecha seleccionada cuando se hace clic en una fecha
                        selectedDate.set(year, month, dayOfMonth)
                        isDateSelectedManually = true // Marcar que el usuario ha seleccionado una fecha
                    }
                }
            },
            update = { calendarView ->
                // Si la fecha no ha sido seleccionada manualmente, establecer la fecha actual
                if (!isDateSelectedManually) {
                    calendarView.date = selectedDate.timeInMillis
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra la fecha seleccionada
        Text(
            text = "Fecha seleccionada: ${selectedDate.get(Calendar.DAY_OF_MONTH)}/" +
                    "${selectedDate.get(Calendar.MONTH) + 1}/" + // Los meses en CalendarView empiezan desde 0
                    "${selectedDate.get(Calendar.YEAR)}",
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun PreviewHistorialScreen() {
    HistotialScreen()
}
