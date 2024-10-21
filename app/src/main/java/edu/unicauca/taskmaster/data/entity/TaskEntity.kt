package edu.unicauca.taskmaster.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) // ID autogenerado
    val id: Int=0,
    @ColumnInfo(name = "task_name")
    val taskName: String, // Obligatorio
    @ColumnInfo(name = "selected_days")
    val selectedDays: String? = null, // Opcional, ahora permite null
    @ColumnInfo(name = "reminder_type")
    val reminderType: String? = null, // Opcional, ahora permite null
    @ColumnInfo(name = "is_input_valid")
    val isInputValid: Boolean? = null // Opcional, ahora permite null
)