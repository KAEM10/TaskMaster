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
fun HabitScreen(
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
                    HabitSection()
                    Spacer(modifier = Modifier.height(20.dp))
                    SaveButton()
                }
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
fun HabitSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(blue)
            .padding(16.dp)
    ){
        Column{
            NameSection()
            HorizontalDivider(thickness = 3.dp, color = black, modifier = Modifier.padding(vertical = 24.dp))
            ListDaysSection()
            HorizontalDivider(thickness = 3.dp, color = black, modifier = Modifier.padding(vertical = 24.dp))
            ReminderSection()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameSection(
    modifier: Modifier = Modifier
){
    var habitName by remember { mutableStateOf("") }
    Text(
        text = "Nombre",
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = habitName,
        onValueChange = { habitName = it },  // Actualiza la variable habitName
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
        DayButton("D")
        DayButton("L")
        DayButton("M")
        DayButton("M")
        DayButton("J")
        DayButton("V")
        DayButton("S")
    }
}

@Composable
fun ReminderSection(
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
        ReminderButton("Mañana", Modifier.weight(1f))
        ReminderButton("Tarde", Modifier.weight(1f))
        ReminderButton("Noche", Modifier.weight(1f))
        ReminderButton("Todas", Modifier.weight(1f),selected = true)
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
        Text(text = day, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ReminderButton(text: String, modifier: Modifier = Modifier, selected: Boolean = false) {
    TextButton(
        onClick = { /* Acción del botón */ },
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
fun SaveButton() {
    Button(
        onClick = { /* TODO: Handle save */ },
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
    HabitScreen()
}
