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
    onDaySelected: (String) -> Unit,
    onReminderSelected: (String) -> Unit,
    onSaveButtonClicked: () -> Unit,
    onCalendarClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onMenuClicked: () -> Unit,
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
                        onDaySelected = { onDaySelected(it) },
                        onReminderSelected = { onReminderSelected(it) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SaveButton(
                        onSaveButtonClicked = { onSaveButtonClicked() }
                    )
                }
            }

            NavBar(
                modifier = Modifier.fillMaxWidth().zIndex(1f),
                onCalendarClicked = { onCalendarClicked() },
                onAddClicked = { onAddClicked() },
                onHomeClicked = { onHomeClicked() },
                onSettingsClicked = { onMenuClicked() }
            )
        }
    }
}



@Composable
fun HabitSection(
    habitName: String,
    onHabitNameChanged: (String) -> Unit,
    onDaySelected: (String) -> Unit,
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
                onDaySelected = { onDaySelected(it) }
            )
            HorizontalDivider(thickness = 3.dp, color = black, modifier = Modifier.padding(vertical = 24.dp))
            ReminderSection(
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
    
    Text(
        text = stringResource(id = R.string.name),
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = habitName,
        onValueChange = { onHabitNameChanged(it) },  // Actualiza la variable habitName
        label = { Text("Nombre del hábito") },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, black, RoundedCornerShape(20.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = blue_3
        ),
    )
}

@Composable
fun ListDaysSection(
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
        DayButton(day = "D",
            onDaySelected = { onDaySelected(it) })
        DayButton("L",
            onDaySelected = { onDaySelected(it) })
        DayButton("M",
            onDaySelected = { onDaySelected(it) })
        DayButton("M",
            onDaySelected = { onDaySelected(it) })
        DayButton("J",
            onDaySelected = { onDaySelected(it) })
        DayButton("V",
            onDaySelected = { onDaySelected(it) })
        DayButton("S",
            onDaySelected = { onDaySelected(it) })
    }
}

@Composable
fun ReminderSection(
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
        ReminderButton(text = "Mañana",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f))
        ReminderButton(text = "Tarde",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f))
        ReminderButton(text = "Noche",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f))
        ReminderButton(text = "Todas",
            onSelectedChanged = { onReminderSelected(it) },
            modifier = Modifier.weight(1f),
            selected = true)
    }
}

@Composable
fun DayButton(
    day: String,
    onDaySelected: (String) -> Unit) {
    TextButton(
        onClick = { onDaySelected(day) },
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(50))
            .background(Color(0xFF90CAF9), RoundedCornerShape(50))
    ) {
        Text(text = day, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ReminderButton(text: String,
                   modifier: Modifier = Modifier,
                   selected: Boolean = false,
                   onSelectedChanged: (String) -> Unit) {
    TextButton(
        onClick = { onSelectedChanged(text) },
        modifier = modifier
            .padding(4.dp)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = black,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (selected) black else blue_3,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(text = text, color = if (selected) Color.White else black, fontWeight = FontWeight.Bold)
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
        onDaySelected = {},
        onReminderSelected = {},
        onSaveButtonClicked = {},
        onCalendarClicked = {},
        onAddClicked = {},
        onHomeClicked = {},
        onMenuClicked = {}
    )
}
