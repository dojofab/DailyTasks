package com.example.dailytasks.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailytasks.ui.configure.ConfigureScreen
import com.example.dailytasks.ui.details.DetailsScreen
import com.example.dailytasks.ui.list.ListScreen
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-01-2023
 */
private enum class Screen{
    LIST,
    DETAILS,
    CONFIGURE,
}

@Composable
fun AppNavHost(taskViewModel: TaskViewModel){
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        NavHost(
            navController = navController,
            startDestination = Screen.LIST.name
        ){
            composable(Screen.LIST.name){
                ListScreen(
                    taskViewModel = taskViewModel,
                    onConfigure = { navController.navigate(Screen.CONFIGURE.name) },
                    onTaskClick = { navController.navigate(Screen.DETAILS.name) }
                )
            }
            composable(Screen.DETAILS.name){
                DetailsScreen(
                    taskViewModel = taskViewModel,
                    onEdit = { navController.navigate(Screen.CONFIGURE.name) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(Screen.CONFIGURE.name){
                ConfigureScreen(
                    taskViewModel = taskViewModel,
                    onConfigureDone = { navController.popBackStack() }
                )
            }
        }
    }
}