package edu.unicauca.taskmaster.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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


enum class TaskMasterScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    CreateTask(title = R.string.create_task),
    Calendar(title = R.string.calendar),
    Settings(title = R.string.settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskMasterAppBar(
    currentScreen: TaskMasterScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun TaskMasterApp(
    createTaskViewModel: CreateTaskViewModel = viewModel(),
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
        val createTaskUiState by createTaskViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TaskMasterScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = TaskMasterScreen.CreateTask.name) {
                CreateTaskScreen(
                    habitName = createTaskUiState.taskName,
                    onHabitNameChanged = { createTaskViewModel.onHabitNameChanged(it) },
                    selectedDays = createTaskUiState.selectedDays,
                    onDaySelected = { createTaskViewModel.onDaySelected(it) },
                    reminderSelected = createTaskUiState.reminderType,
                    onReminderSelected = { createTaskViewModel.onReminderSelected(it) },
                    onSaveButtonClicked = { createTaskViewModel.onSaveButtonClicked() }
                )
            }

            composable(route = TaskMasterScreen.Home.name) {
                HomeScreen()
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