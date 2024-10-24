package edu.unicauca.taskmaster.data.model

data class Task(
    val taskName: String,
    val selectedDays: List<String> = emptyList(),
    val reminderType: String)
