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
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.data.entity.TaskEntity
import edu.unicauca.taskmaster.data.model.Task
import edu.unicauca.taskmaster.data.repository.TaskRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    // Estado del formulario
    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> = _uiState.asStateFlow()

    // Manejar cambios en el nombre del hábito
    fun onTaskNameChanged(habitName: String) {
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

        //Construir el objeto task
        val newTask = Task(
            taskName = taskName,
            selectedDays = selectedDays.split(", "),
            reminderType = reminderType
        )

        if (taskName.isNotEmpty() && selectedDays.isNotEmpty() && reminderType.isNotEmpty()) {
            val message = "Tarea: $taskName\nDías: $selectedDays\nRecordatorio: $reminderType"
            // Llama a un método para mostrar la alerta
            askIfSaveTask(context, message,true, newTask)
        }else{
            val message = "Todos los campos son requeridos"
            // Llama a un método para mostrar la alerta
            askIfSaveTask(context, message,false)
        }
    }

    private fun askIfSaveTask(context: Context, message: String, save:Boolean, newTask: Task? = null) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Guardar Tarea")
        builder.setMessage(message)  // Usa el mensaje dl argumento
        builder.setPositiveButton("Aceptar") { dialog, which ->
            if(save){
               // Toast.makeText(context, "Se ha guardado la Tarea", Toast.LENGTH_LONG).show()

                //Guardar la tarea en la base de datos
                if (newTask != null) {
                    viewModelScope.launch {
                        taskRepository.saveTask(newTask)
                        showCustomToast(context, "Se ha guardado la Tarea")
                        resetForm()
                    }
                } else showCustomToast(context, "Error al guardar la tarea")
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