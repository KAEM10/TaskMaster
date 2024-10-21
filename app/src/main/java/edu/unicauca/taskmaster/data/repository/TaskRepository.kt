package edu.unicauca.taskmaster.data.repository

import edu.unicauca.taskmaster.data.dao.TaskDao
import edu.unicauca.taskmaster.data.model.TaskItem
import edu.unicauca.taskmaster.data.source.TaskLocalDataSource
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
)
{
    fun getTasks(): List<TaskItem> {
        return taskDao.getAll().map { TaskItem(it.id, it.taskName) }
    }
}