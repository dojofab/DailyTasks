package com.example.dailytasks.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dailytasks.data.Duration
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Donn Fabian on 02-01-2023
 */

interface TaskViewModelAbstract {
    val selectedTaskState: State<TaskEntity?>
    val duration: State<Duration?>
    val tasksListFlow: Flow<List<TaskEntity>>
    fun addOrUpdateTask(task: TaskEntity)
    fun deleteTask(task: TaskEntity)
    fun selectTask(task: TaskEntity)
    fun resetTask()
    fun checkDuration(duration: Duration)
    fun resetDurationValidation()
}

@HiltViewModel
class TaskViewModel @Inject constructor(
   private val taskRepository: TaskRepository
): ViewModel(), TaskViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _selectedTaskState: MutableState<TaskEntity?> = mutableStateOf(null)
    override val selectedTaskState: State<TaskEntity?>
        get() = _selectedTaskState

    private val _isDurationValid = MutableStateFlow(true)
    val isDurationValid: StateFlow<Boolean> = _isDurationValid

    private val _duration: MutableState<Duration?> = mutableStateOf(null)
    override val duration: State<Duration?> = _duration

    override val tasksListFlow: Flow<List<TaskEntity>> = taskRepository.getTasks()

    override fun selectTask(task: TaskEntity){
        _selectedTaskState.value = task
    }

    override fun checkDuration(duration: Duration){

        _duration.value = duration

        //check if duration is valid
        val isDurationValid = duration.value > 0
        _isDurationValid.value = isDurationValid
    }

    override fun resetDurationValidation(){
        _isDurationValid.value = true
    }

    override fun addOrUpdateTask(task: TaskEntity) {
        ioScope.launch {
            task.id?.let {
                taskRepository.update(task)
            }?: run{
                taskRepository.insert(task)
            }
        }
    }

    override fun deleteTask(task: TaskEntity) {
        ioScope.launch {
            taskRepository.delete(task)
        }
    }

    override fun resetTask() {
        _selectedTaskState.value = null
    }
}