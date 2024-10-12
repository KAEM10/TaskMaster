package edu.unicauca.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import edu.unicauca.taskmaster.ui.screens.config.ConfigScreen
import edu.unicauca.taskmaster.ui.screens.home.HomeScreen
import edu.unicauca.taskmaster.ui.screens.create.HabitScreen
import edu.unicauca.taskmaster.ui.screens.historial.HistotialScreen
import edu.unicauca.taskmaster.ui.theme.TaskMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //HomeScreen(modifier = Modifier.fillMaxSize())
                    //WelcomeScreen(blurRadius = 0f)
                    //HabitScreen(modifier = Modifier.fillMaxSize())
                    //ConfigScreen(modifier = Modifier.fillMaxSize())
                    HistotialScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}