package edu.unicauca.taskmaster.ui.screens.create

data class CreateTaskUiState(
    val taskName: String = "",
    val selectedDays: List<String> = emptyList(),
    val reminderType: String = "",
    val isInputValid: Boolean = false
)
