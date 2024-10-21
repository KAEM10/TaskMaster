package edu.unicauca.taskmaster.data.repository

import android.util.Log
import edu.unicauca.taskmaster.data.dao.TaskDao
import edu.unicauca.taskmaster.data.entity.TaskEntity
import edu.unicauca.taskmaster.data.model.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
)
{
    private val TAG = "TaskRepository"
    private var idTask = 0

    suspend fun getTasks(): List<Task> {
        Log.d(TAG, "getTasks: ")
        return taskDao.getAll().map {
            Task(
                taskName = it.taskName,
                selectedDays = it.selectedDays.split(", "),
                reminderType = it.reminderType)
        }
    }

    suspend fun saveTask(task: Task) {
        Log.d(TAG, "saveTask: $task")
        idTask++
        taskDao.insertAll(
            TaskEntity(
                id = idTask,
                taskName = task.taskName,
                selectedDays = task.selectedDays.joinToString(", "),
                reminderType = task.reminderType
            )
        )
    }

    suspend fun deleteTask(task: TaskEntity) {
        Log.d(TAG, "deleteTask: $task")
        idTask--
        taskDao.delete(task)
    }
}