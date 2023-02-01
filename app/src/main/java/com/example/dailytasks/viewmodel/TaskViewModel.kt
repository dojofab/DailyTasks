package com.example.dailytasks.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dailytasks.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Donn Fabian on 02-01-2023
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {
}