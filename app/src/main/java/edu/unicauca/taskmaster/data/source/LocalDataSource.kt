package edu.unicauca.taskmaster.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.unicauca.taskmaster.data.dao.TaskDao
import edu.unicauca.taskmaster.data.entity.TaskEntity

/**
 * https://developer.android.com/training/data-storage/room?hl=es-419#db-migration-testing
 *
 */
@Database(entities = [TaskEntity::class], version = 2)
abstract class LocalDataSource : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}