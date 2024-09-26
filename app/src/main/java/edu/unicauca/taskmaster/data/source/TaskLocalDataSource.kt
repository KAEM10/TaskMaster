package edu.unicauca.taskmaster.data.source

import edu.unicauca.taskmaster.data.model.TaskItem

class TaskLocalDataSource {
    fun getTasks() = List(15) { i -> TaskItem(i, "Task # $i") }
}