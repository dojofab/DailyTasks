package com.example.dailytasks.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Donn Fabian on 02-01-2023
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
    taskRepository: TaskRepository
): ViewModel() {

    private val _selectedTaskState: MutableState<TaskEntity?> = mutableStateOf(null)
    val selectedTaskState: State<TaskEntity?>
        get() = _selectedTaskState

    val tasksListFlow: Flow<List<TaskEntity>> = taskRepository.getTasks()

    fun selectTask(taskEntity: TaskEntity){
        _selectedTaskState.value = taskEntity
    }
}