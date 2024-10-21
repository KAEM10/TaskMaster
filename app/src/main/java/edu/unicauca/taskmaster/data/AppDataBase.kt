package edu.unicauca.taskmaster.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.unicauca.taskmaster.data.dao.TaskDao
import edu.unicauca.taskmaster.data.entity.TaskEntity

/**
 * https://developer.android.com/training/data-storage/room?hl=es-419#db-migration-testing
 *
 */
@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}