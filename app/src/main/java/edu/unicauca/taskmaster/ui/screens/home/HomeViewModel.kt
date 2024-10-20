package edu.unicauca.taskmaster.ui.screens.home

import androidx.lifecycle.ViewModel
import edu.unicauca.taskmaster.data.repository.TaskRepository
import edu.unicauca.taskmaster.data.source.TaskLocalDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(): ViewModel() {
    private lateinit var taskRepository: TaskRepository
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    init {
        taskRepository = TaskRepository(TaskLocalDataSource())
        getTask()
    }

    private fun getTask() {
        val task = taskRepository.getTask()
        _uiState.update { it.copy(task = task) }
    }
}