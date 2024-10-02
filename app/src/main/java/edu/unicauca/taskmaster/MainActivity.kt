package edu.unicauca.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import edu.unicauca.taskmaster.ui.theme.TaskMasterTheme
import edu.unicauca.taskmaster.ui.screens.home.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //HomeScreen()
                    WelcomeScreen(blurRadius = 0f)
                }
            }
        }
    }
}