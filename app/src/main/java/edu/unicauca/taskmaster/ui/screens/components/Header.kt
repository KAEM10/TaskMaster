package edu.unicauca.taskmaster.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import edu.unicauca.taskmaster.ui.theme.*



@Composable
fun HeaderTask(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(red)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.icono_task_master),
            modifier = Modifier.size(45.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Tareas de Hoy",
            color = Color.Black,
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