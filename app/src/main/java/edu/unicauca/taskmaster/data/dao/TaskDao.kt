package edu.unicauca.taskmaster.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.unicauca.taskmaster.data.entity.TaskEntity


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM tasks WHERE id IN (:Ids)")
    fun loadAllByIds(Ids: IntArray): List<TaskEntity>

    @Insert
    fun insertAll(vararg task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}