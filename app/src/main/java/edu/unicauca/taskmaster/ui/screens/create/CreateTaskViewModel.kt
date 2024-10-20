package edu.unicauca.taskmaster.ui.screens.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import edu.unicauca.taskmaster.R

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

        if (taskName.isNotEmpty() && selectedDays.isNotEmpty() && reminderType.isNotEmpty()) {
            val message = "Tarea: $taskName\nDías: $selectedDays\nRecordatorio: $reminderType"
            // Llama a un método para mostrar la alerta
            showAlert(context, message,true)
        }else{
            val message = "Todos los campos son requeridos"
            // Llama a un método para mostrar la alerta
            showAlert(context, message,false)
        }
    }

    private fun showAlert(context: Context, message: String ,save:Boolean) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Guardar Tarea")
        builder.setMessage(message)  // Usa el mensaje dl argumento
        builder.setPositiveButton("Aceptar") { dialog, which ->
            if(save){
                //TODO: aca guardar la info en la bd
               // Toast.makeText(context, "Se ha guardado la Tarea", Toast.LENGTH_LONG).show()

                showCustomToast(context, "Se ha guardado la Tarea")
                resetForm()
                showCustomToast(context, "Se ha guardado la Tarea")
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
    private fun showCustomToast(context: Context, message: String) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.toast_custom, null)

        // Establece el texto del mensaje
        val textView = layout.findViewById<TextView>(R.id.toast_text)
        textView.text = message

        // Crea el Toast
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout

        // Cambia la posición del Toast
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 100)

        // Muestra el Toast
        toast.show()
    }
    private fun resetForm() {
        _uiState.value = CreateTaskUiState(
            taskName = "",
            selectedDays = emptyList(),
            reminderType = ""
        )
    }
}