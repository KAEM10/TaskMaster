package edu.unicauca.taskmaster.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.taskmaster.ui.theme.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import edu.unicauca.taskmaster.R


@Composable
fun CustomBottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF4444))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly  // Espaciado entre los íconos
    ) {
        IconButton(onClick = { /* Acción de inicio */ }) {
            Icon(painterResource(R.drawable.icon_home), contentDescription = "Inicio", tint = Color.Black)
        }
        IconButton(onClick = { /* Acción de agregar */ }) {
            Icon(painterResource(R.drawable.icon_add_box), contentDescription = "Agregar", tint = Color.Black)
        }
        IconButton(onClick = { /* Acción de calendario */ }) {
            Icon(painterResource(R.drawable.icon_calendar), contentDescription = "Calendario", tint = Color.Black)
        }
        IconButton(onClick = { /* Acción de menú */ }) {
            Icon(painterResource(R.drawable.icon_menu), contentDescription = "Menú", tint = Color.Black)
        }
    }
}

@Preview
@Composable

fun HeaderPreview() {
    TaskMasterTheme {
        CustomBottomNavigationBar()
    }
}