package edu.unicauca.taskmaster.data.repository

import edu.unicauca.taskmaster.data.model.TaskItem
import edu.unicauca.taskmaster.data.source.TaskLocalDataSource

class TaskRepository (private val taskLocalDataSource: TaskLocalDataSource){
    fun getTask(): List<TaskItem> {
        return taskLocalDataSource.getTasks()
    }
}