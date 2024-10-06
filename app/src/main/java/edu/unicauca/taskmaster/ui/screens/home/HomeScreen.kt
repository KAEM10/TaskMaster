package edu.unicauca.taskmaster.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.data.model.TaskItem
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import edu.unicauca.taskmaster.ui.theme.*

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val homeUiState by homeViewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        BackgroundWithCircles(blurRadius = 0.4f)

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderTask(
                imagen = R.drawable.icono_task_master,
                textoHeader = stringResource(R.string.HomeScreenTitle),
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f) // Colocamos el header por encima del fondo
            )
            // Lista de tareas, que ocupa el espacio restante
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                TasksList(list = homeUiState.task)
            }
            NavBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f) // Aseguramos que el NavBar esté visible por encima del fondo
            )
        }
    }
}


@Composable
fun TaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (checked) gray else blue

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, black, RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(36.dp)
        )
        Text(
            fontWeight = FontWeight.Bold,
            text = taskName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        IconButton(onClick = onClose) {
            Icon(
                painter = painterResource(R.drawable.icon_settings),
                contentDescription = "Close",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun TaskItem(taskName: String, modifier: Modifier = Modifier) {
    var checkedState by remember { mutableStateOf(false) }

    TaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = {}, // we will implement this later!
        modifier = modifier,
    )
}

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    list: List<TaskItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(list) { task ->
            TaskItem(taskName = task.label)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TaskMasterTheme {
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

