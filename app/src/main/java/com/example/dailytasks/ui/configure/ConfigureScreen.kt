package com.example.dailytasks.ui.configure

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dailytasks.R
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.util.showToast
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun ConfigureScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onConfigureDone: () -> Unit,
){
    StatefulConfigureScreen(
        modifier = modifier,
        taskViewModel = taskViewModel,
    ) {
        onConfigureDone()
    }
}

@Composable
fun StatefulConfigureScreen(
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    onConfigureDone: () -> Unit,
){

    val taskEntity = taskViewModel.selectedTaskState.value

    val taskName = remember { mutableStateOf(taskEntity?.name?: "") }
    val length =  remember { mutableStateOf(taskEntity?.length?: 0) }
    val colorSelected = remember{ mutableStateOf(taskEntity?.theme?: "") }

    StatelessConfigureScreen(
        modifier = modifier,
        taskViewModel = taskViewModel,
        taskEntity = taskEntity,
        taskName = taskName,
        colorSelected = colorSelected,
        length = length
    ) {
        onConfigureDone()
    }
}

@Composable
fun StatelessConfigureScreen(
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    taskEntity: TaskEntity?,
    taskName: MutableState<String>,
    colorSelected: MutableState<String>,
    length: MutableState<Int>,
    onConfigureDone: () -> Unit
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                modifier = Modifier,
                taskViewModel = taskViewModel,
                taskEntity = taskEntity){
                onConfigureDone()
            }
        }
    ) { contentPadding ->

        StatefulContent(
            modifier = Modifier.padding(contentPadding),
            taskViewModel = taskViewModel,
            taskEntity = taskEntity,
            taskName = taskName,
            colorSelected = colorSelected,
            length = length
        ) {
            onConfigureDone()
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    taskEntity: TaskEntity?,
    onConfigureDone: () -> Unit
){
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "",
        ) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            taskViewModel.selectedTaskState.value?.let {
                IconButton(onClick = {
                    taskViewModel.deleteTask(taskEntity!!)
                    onConfigureDone()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription =  stringResource(id = R.string.delete_icon),
                        tint = Color.Red,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun StatefulContent(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    taskEntity: TaskEntity?,
    taskName: MutableState<String>,
    colorSelected: MutableState<String>,
    length: MutableState<Int>,
    onConfigureDone: () -> Unit
){

    val colorPicker = remember { mutableStateOf(false) }
    val durationPicker = remember { mutableStateOf(false) }

    if (colorPicker.value) {
        ColorPickerDialog(openDialogCustom = colorPicker){
            colorSelected.value = it
            colorPicker.value = false
        }
    }

    if(durationPicker.value){
        taskViewModel.resetDurationValidation()
        DurationPickerDialog(
            openDialogCustom = durationPicker,
            onClose = {
                durationPicker.value = false
            }){
            taskViewModel.checkDuration(it)
            durationPicker.value = false
        }
    }

    when(taskViewModel.isDurationValid.collectAsState().value){
        true ->{
            taskViewModel.duration.value?.let {
                length.value = it
            }
        }
        false -> LocalContext.current.showToast(stringResource(id = R.string.invalid_duration))
    }

    StatelessContent(
        modifier = modifier,
        taskViewModel = taskViewModel,
        taskEntity = taskEntity,
        taskName = taskName,
        colorSelected = colorSelected,
        length = length,
        durationPicker = durationPicker,
        colorPicker = colorPicker
    ) {
        onConfigureDone()
    }
}

@Composable
fun StatelessContent(
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    taskEntity: TaskEntity?,
    taskName: MutableState<String>,
    colorSelected: MutableState<String>,
    length: MutableState<Int>,
    durationPicker: MutableState<Boolean>,
    colorPicker: MutableState<Boolean>,
    onConfigureDone: () -> Unit
){
    Column(
        modifier = modifier
    ){
        DataScreen(
            modifier = modifier,
            taskName = taskName,
            colorSelected = colorSelected,
            length = length,
            durationPicker = durationPicker,
            colorPicker = colorPicker
        )
        Spacer(modifier = Modifier.weight(1f))
        ControlsScreen(
            modifier = modifier,
            taskViewModel = taskViewModel,
            taskEntity = taskEntity,
            taskName = taskName,
            colorSelected = colorSelected,
            length = length
        ) {
            onConfigureDone()
        }
    }
}
