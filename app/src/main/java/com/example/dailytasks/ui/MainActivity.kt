package com.example.dailytasks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.dailytasks.ui.theme.DailyTasksTheme
import com.example.dailytasks.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskViewModel: TaskViewModel by viewModels()

        setContent {
            DailyTasksTheme {
               AppNavHost(taskViewModel)
            }
        }
    }
}