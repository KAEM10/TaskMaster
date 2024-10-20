package edu.unicauca.taskmaster.ui.screens.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateTaskViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> = _uiState.asStateFlow()

    fun onHabitNameChanged(habitName: String) {
        _uiState.value = _uiState.value.copy(taskName = habitName)
    }

    fun onDaySelected(day: String) {
        _uiState.value = _uiState.value.copy(selectedDays = _uiState.value.selectedDays + day)

    }

    fun onReminderSelected(reminder: String) {
        _uiState.value = _uiState.value.copy(reminderType = reminder)
    }

    fun onSaveButtonClicked() {
        // Aquí puedes implementar la lógica para guardar la tarea
        TODO("Not yet implemented")
    }


}