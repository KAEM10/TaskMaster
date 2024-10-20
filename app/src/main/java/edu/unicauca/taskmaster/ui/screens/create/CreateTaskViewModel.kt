package edu.unicauca.taskmaster.ui.screens.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.app.AlertDialog
import android.content.Context
class CreateTaskViewModel: ViewModel() {
    // Estado del formulario
    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> = _uiState.asStateFlow()

    // Manejar cambios en el nombre del hábito
    fun onHabitNameChanged(habitName: String) {
        _uiState.value = _uiState.value.copy(taskName = habitName)
    }

    // Manejar selección de días
    fun onDaySelected(day: String) {
        val selectedDays = _uiState.value.selectedDays.toMutableList()
        if (selectedDays.contains(day)) {
            selectedDays.remove(day) // Desmarcar el día si ya está seleccionado
        } else {
            selectedDays.add(day) // Marcar el día
        }
        _uiState.value = _uiState.value.copy(selectedDays = selectedDays)

    }

    fun onReminderSelected(reminder: String) {
        _uiState.value = _uiState.value.copy(reminderType = reminder)
    }

    fun onSaveButtonClicked(context: Context) {
        val taskName = _uiState.value.taskName
        val selectedDays = _uiState.value.selectedDays.joinToString(", ")
        val reminderType = _uiState.value.reminderType

        // Aquí puedes implementar la lógica para mostrar la alerta
        if (taskName.isNotEmpty() && selectedDays.isNotEmpty() && reminderType.isNotEmpty()) {
            val message = "Tarea: $taskName\nDías: $selectedDays\nRecordatorio: $reminderType"
            // Llama a un método para mostrar la alerta
            showAlert(context, message)  // Asegúrate de pasar el contexto correcto
        }else{
            val message = "Todos los campos son requeridos"
            // Llama a un método para mostrar la alerta
            showAlert(context, message)  // Asegúrate de pasar el contexto correcto
        }
    }

    private fun showAlert(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Guardar Tarea")
        builder.setMessage(message)  // Usa el mensaje dl argumento
        builder.setPositiveButton("Aceptar") { dialog, which ->

        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }


}