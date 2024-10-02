package edu.unicauca.taskmaster.data.source

import edu.unicauca.taskmaster.data.model.TaskItem

class TaskLocalDataSource {
    fun getTasks() = List(4) { i -> TaskItem(i, "Task # $i") }
}