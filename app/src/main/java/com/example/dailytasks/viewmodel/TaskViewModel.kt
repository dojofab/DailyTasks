package com.example.dailytasks.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dailytasks.data.Duration
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _isDurationValid = MutableStateFlow(true)
    val isDurationValid: StateFlow<Boolean> = _isDurationValid

    private val _length = MutableStateFlow<Long>(0)
    val length: StateFlow<Long> = _length

    val tasksListFlow: Flow<List<TaskEntity>> = taskRepository.getTasks()

    fun selectTask(taskEntity: TaskEntity){
        _selectedTaskState.value = taskEntity
    }

    fun checkDuration(duration: Duration){
        val isDurationValid = duration.value.isNotEmpty() && duration.value.toInt() > 0
        _isDurationValid.value = isDurationValid

        if(isDurationValid){
            var multiplier: Long = 0

            when(duration.unit){
                "hours" -> multiplier = 3600
                "minutes" -> multiplier = 60
                "seconds" -> multiplier = 1
            }

            _length.value = (duration.value.toLong()  * multiplier)
        }
    }

    fun resetDurationValidation(){
        _isDurationValid.value = true
    }
}