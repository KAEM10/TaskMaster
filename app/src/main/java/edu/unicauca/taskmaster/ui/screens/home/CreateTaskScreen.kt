package edu.unicauca.taskmaster.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import edu.unicauca.taskmaster.ui.theme.*

@Composable
fun HabitScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderTask(
                imagen = R.drawable.icon_close,
                textoHeader ="",
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )

            // Habit Name Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(blue)
                    .padding(16.dp)
            ) {
                HabitNameSection()
            }

            SaveButton()

            NavBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun HabitNameSection() {
    var habitName by remember { mutableStateOf("") }

    Column {
        Text(
            text = "Nombre",
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = habitName,
            onValueChange = { habitName = it },  // Actualiza la variable habitName
            label = { Text("Nombre del hábito") },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, black, RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(20.dp))
        // Repeat Days
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DayButton("D")
            DayButton("L")
            DayButton("M")
            DayButton("M")
            DayButton("J")
            DayButton("V")
            DayButton("S")
        }
        Spacer(modifier = Modifier.height(20.dp))
        // Reminder section
        Text(
            text = "Recordatorio",
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Reminder options
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ReminderButton("Mañana")
            ReminderButton("Tarde", selected = true) // Example with selection
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ReminderButton("Noche")
            ReminderButton("Todas")
        }
    }
}

@Composable
fun DayButton(day: String) {
    TextButton(
        onClick = { /* Handle day selection */ },
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(50))
            .background(Color(0xFF90CAF9), RoundedCornerShape(50))
    ) {
        Text(text = day, color = Color.Black)
    }
}

@Composable
fun ReminderButton(text: String, selected: Boolean = false) {
    TextButton(
        onClick = { /* Handle reminder selection */ },
        modifier = Modifier

            .padding(4.dp)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) Color.Black else Color.Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (selected) Color.Black else Color(0xFF90CAF9),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(text = text, color = if (selected) Color.White else Color.Black)
    }
}

@Composable
fun SaveButton() {
    Button(
        onClick = { /* TODO: Handle save */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(blue)
    ) {
        Text(text = stringResource(R.string.btn_guardar), color = Color.Black)
    }
}

@Preview
@Composable
fun HabitScreenPreview() {
    HabitScreen()
}
