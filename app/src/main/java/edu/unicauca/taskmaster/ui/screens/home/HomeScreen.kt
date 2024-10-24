package edu.unicauca.taskmaster.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.data.model.Task
import edu.unicauca.taskmaster.ui.screens.components.BackgroundWithCircles
import edu.unicauca.taskmaster.ui.screens.components.HeaderTask
import edu.unicauca.taskmaster.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TaskItemViewModel : ViewModel() {
    // Usamos un mapa para asociar cada tarea con su estado
    private val _taskStates = MutableStateFlow<Map<Int, TaskState>>(emptyMap())
    val taskStates: StateFlow<Map<Int, TaskState>> = _taskStates

    // Función para cambiar el estado del checkbox de una tarea específica
    fun onCheckedChange(taskId: Int, checked: Boolean) {
        _taskStates.update { currentStates ->
            val updatedState = currentStates.toMutableMap()
            updatedState[taskId] = TaskState(checked = checked)
            updatedState
        }
    }

    data class TaskState(
        val checked: Boolean = false
    )
}

@Composable
fun HomeScreen(
    list: List<Task> = listOf(),
    viewModel: TaskItemViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
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
                    .zIndex(1f)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                TasksList(list = list, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TaskItem(
    taskId: Int,
    taskName: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isChecked) gray else blue

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(36.dp)
        )
        Text(
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
fun TasksList(
    list: List<Task>,
    viewModel: TaskItemViewModel,
    modifier: Modifier = Modifier
) {
    // Obtenemos los estados de las tareas desde el ViewModel
    val taskStates by viewModel.taskStates.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        itemsIndexed(list) { index, task ->
            val taskState = taskStates[index] ?: TaskItemViewModel.TaskState()
            TaskItem(
                taskId = index,
                taskName = task.taskName,
                isChecked = taskState.checked,
                onCheckedChange = { isChecked -> viewModel.onCheckedChange(index, isChecked) },
                onClose = { /* Aquí puedes manejar la lógica de cierre */ }
            )
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

