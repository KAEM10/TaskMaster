package edu.unicauca.taskmaster.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.theme.TaskMasterTheme
import edu.unicauca.taskmaster.ui.theme.red

@Composable
fun HeaderTask() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(red)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(R.drawable.icono_task_master), contentDescription = null)
        Text(
            text = "Tareas de Hoy",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun HeaderTaskPreview() {
    TaskMasterTheme {
        HeaderTask()
    }
}