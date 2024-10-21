package edu.unicauca.taskmaster.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.unicauca.taskmaster.R
import edu.unicauca.taskmaster.ui.screens.components.NavBar
import edu.unicauca.taskmaster.ui.screens.config.ConfigScreen
import edu.unicauca.taskmaster.ui.screens.create.CreateTaskViewModel
import edu.unicauca.taskmaster.ui.screens.create.CreateTaskScreen
import edu.unicauca.taskmaster.ui.screens.historial.HistotialScreen
import edu.unicauca.taskmaster.ui.screens.home.HomeScreen
import edu.unicauca.taskmaster.ui.screens.home.HomeViewModel


enum class TaskMasterScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    CreateTask(title = R.string.create_task),
    Calendar(title = R.string.calendar),
    Settings(title = R.string.settings)
}

@Composable
fun TaskMasterApp(
    createTaskViewModel: CreateTaskViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TaskMasterScreen.valueOf(
        backStackEntry?.destination?.route ?: TaskMasterScreen.Home.name
    )
    Scaffold(
        bottomBar = {
            NavBar(
                onHomeClicked = {
                    navController.navigate(TaskMasterScreen.Home.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                },
                onAddClicked = {
                    navController.navigate(TaskMasterScreen.CreateTask.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                },
                onCalendarClicked = {
                    navController.navigate(TaskMasterScreen.Calendar.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                },
                onSettingsClicked = {
                    navController.navigate(TaskMasterScreen.Settings.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                }
            )
        }

    ) { innerPadding ->
        val homeUiState by homeViewModel.uiState.collectAsState()
        val createTaskUiState by createTaskViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TaskMasterScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = TaskMasterScreen.CreateTask.name) {
                val context = LocalContext.current  // Obtén el contexto local
                CreateTaskScreen(
                    habitName = createTaskUiState.taskName,
                    onHabitNameChanged = { createTaskViewModel.onTaskNameChanged(it) },
                    selectedDays = createTaskUiState.selectedDays,
                    onDaySelected = { createTaskViewModel.onDaySelected(it) },
                    reminderSelected = createTaskUiState.reminderType,
                    onReminderSelected = { createTaskViewModel.onReminderSelected(it) },
                    onSaveButtonClicked = { createTaskViewModel.onSaveButtonClicked(context) }
                )
            }

            composable(route = TaskMasterScreen.Home.name) {
                HomeScreen(
                    list = homeUiState.task,
                )
            }

            composable(route = TaskMasterScreen.Calendar.name) {
                HistotialScreen()
            }

            composable(route = TaskMasterScreen.Settings.name) {
                ConfigScreen()
            }
        }
    }
}