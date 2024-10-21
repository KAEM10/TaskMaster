package edu.unicauca.taskmaster.data.dependencies

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.unicauca.taskmaster.data.dao.TaskDao
import edu.unicauca.taskmaster.data.source.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext applicationContext: Context): LocalDataSource {
        return Room.databaseBuilder(
            applicationContext,
            LocalDataSource::class.java, "database-task"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(localDataSource: LocalDataSource): TaskDao {
        return localDataSource.taskDao()
    }

}