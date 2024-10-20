package edu.unicauca.taskmaster.ui.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import edu.unicauca.taskmaster.ui.theme.*

@Composable
fun CreateTaskScreen(
    habitName: String,
    onHabitNameChanged: (String) -> Unit,
    selectedDays: List<String>,
    onDaySelected: (String) -> Unit,
    reminderSelected: String,
    onReminderSelected: (String) -> Unit,
    onSaveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackgroundWithCircles(blurRadius = 0.4f)
        Column(
            modifier = Modifier.fillMaxSize(),
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderTask(
                imagen = R.drawable.icon_close,
                textoHeader = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HabitSection(
                        habitName = habitName,
                        onHabitNameChanged = { onHabitNameChanged(it) },
                        selectedDays = selectedDays,
                        onDaySelected = { onDaySelected(it) },
                        reminderSelected = reminderSelected,
                        onReminderSelected = { onReminderSelected(it) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SaveButton(
                        onSaveButtonClicked = { onSaveButtonClicked() }
                    )
                }
            }
        }
    }
}



@Composable
fun HabitSection(
    habitName: String,
    onHabitNameChanged: (String) -> Unit,
    selectedDays: List<String>,
    onDaySelected: (String) -> Unit,
    reminderSelected: String,
    onReminderSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(blue)
            .padding(16.dp)
    ){
        Column{
            NameSection(
                habitName = habitName,
                onHabitNameChanged = { onHabitNameChanged(it) }
            )
            HorizontalDivider(thickness = 3.dp, color = black, modifier = Modifier.padding(vertical = 24.dp))
            ListDaysSection(
                selectedDays = selectedDays,
                onDaySelected = { onDaySelected(it) }
            )
            HorizontalDivider(thickness = 3.dp, color = black, modifier = Modifier.padding(vertical = 24.dp))
            ReminderSection(
                reminderSelected = reminderSelected,
                onReminderSelected = { onReminderSelected(it) }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameSection(
    habitName: String,
    onHabitNameChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){

    var isFieldEmpty by remember { mutableStateOf(false) }
    var isFieldFocused by remember { mutableStateOf(false) }
    Text(
        text = stringResource(id = R.string.name),
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = habitName,
        onValueChange = { onHabitNameChanged(it)
            isFieldEmpty = it.isEmpty() // Actualiza el estado si el campo está vacío
                        },  // Actualiza la variable habitName
        label = { Text("Nombre del hábito") },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, black, RoundedCornerShape(20.dp))
            .onFocusChanged { focusState ->
                isFieldFocused = focusState.isFocused// Actualiza el estado de enfoque
                if (!isFieldFocused && habitName.isEmpty()) {
                    isFieldEmpty = true // Muestra el mensaje si se pierde el enfoque y está vacío
                } else {
                    isFieldEmpty = false // Oculta el mensaje si hay texto
                }
            },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = blue_3
        ),
    )
    if (isFieldEmpty) {
        Text(
            text = "Este campo es requerido",
            color = Color.Red, // Color rojo para el mensaje de advertencia
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ListDaysSection(
    days: List<String> = listOf("L", "M", "X", "J", "V", "S", "D"),
    selectedDays: List<String>,
    onDaySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Repetir",
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {

        days.forEach { day ->
            DayButton(
                day = day,
                selected = selectedDays.contains(day),  // Verifica si el día está en selectedDays
                onDaySelected = { onDaySelected(it)
                }
            )
        }

    }
}

@Composable
fun ReminderSection(
    reminderSelected: String,
    onReminderSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Recordatorio",
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        ReminderButton(
            reminderSelected = reminderSelected,
            text = "Mañana",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f)
        )
        ReminderButton(
            reminderSelected = reminderSelected,
            text = "Tarde",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f)
        )
        ReminderButton(
            reminderSelected = reminderSelected,
            text = "Noche",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f)
        )
        ReminderButton(
            reminderSelected = reminderSelected,
            text = "Todas",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DayButton(
    selected: Boolean,
    day: String,
    onDaySelected: (String) -> Unit) {
    TextButton(
        onClick = { onDaySelected(day) },
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(50))
            .background(Color(0xFF90CAF9), RoundedCornerShape(50)),
        colors = ButtonDefaults.buttonColors(containerColor = if (selected) Color.Black else Color(0xFF90CAF9))
    ) {
        Text(text = day,
            color = if (selected) Color.White else Color.Black, // Cambia el color del texto según el estado
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ReminderButton(
    reminderSelected: String,
    text: String,
    modifier: Modifier = Modifier,
    onSelectedChanged: (String) -> Unit) {
    TextButton(
        onClick = { onSelectedChanged(text) },
        modifier = modifier
            .padding(4.dp)
            .border(
                width = if (reminderSelected.equals(text)) 2.dp else 1.dp,
                color = black,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (reminderSelected.equals(text)) black else blue_3,
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
        Text(text = text, color = if (reminderSelected.equals(text)) Color.White else black, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SaveButton(
    onSaveButtonClicked: () -> Unit
) {
    Button(
        onClick = { onSaveButtonClicked() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(70.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(blue)
    ) {
        Text(text = stringResource(R.string.btn_guardar), color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun HabitScreenPreview() {
    CreateTaskScreen(
        habitName = "",
        onHabitNameChanged = {},
        selectedDays = listOf(),
        onDaySelected = {},
        reminderSelected = "",
        onReminderSelected = {},
        onSaveButtonClicked = {}
    )
}
