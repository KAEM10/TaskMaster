package edu.unicauca.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.unicauca.taskmaster.data.source.LocalDataSource
import edu.unicauca.taskmaster.data.entity.TaskEntity
import edu.unicauca.taskmaster.ui.screens.TaskMasterApp
import edu.unicauca.taskmaster.ui.theme.TaskMasterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            LocalDataSource::class.java, "database-task"
        ).build()
        // Insertar tarea en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val tarea = TaskEntity(0, "Nueva Tarea")
            db.taskDao().insertAll(tarea)
        }
        setContent {
            TaskMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TaskMasterApp()
                    //HomeScreen(modifier = Modifier.fillMaxSize())
                    //WelcomeScreen(blurRadius = 0f)
                    //HabitScreen(modifier = Modifier.fillMaxSize())
                    //ConfigScreen(modifier = Modifier.fillMaxSize())
                    //HistotialScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}