package edu.unicauca.taskmaster.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun NavBar(
    onHomeClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onCalendarClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(red)
            .padding(8.dp)
            .size(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        IconButton(
            onClick = { onHomeClicked() },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_home),
                contentDescription = "Inicio",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        IconButton(
            onClick = { onAddClicked() },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_add_box),
                contentDescription = "Agregar",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        IconButton(
            onClick = { onCalendarClicked() },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_calendar),
                contentDescription = "Calendario",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
        IconButton(
            onClick = { onSettingsClicked() },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_menu),
                contentDescription = "Menú",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable

fun HeaderPreview() {
    TaskMasterTheme {
        NavBar(
            onHomeClicked = {},
            onAddClicked = {},
            onCalendarClicked = {},
            onSettingsClicked = {}
        )
    }
}