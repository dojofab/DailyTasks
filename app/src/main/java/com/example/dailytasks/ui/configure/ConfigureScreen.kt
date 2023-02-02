package com.example.dailytasks.ui.configure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.dailytasks.R
import com.example.dailytasks.util.showToast
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun ConfigureScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onCancel: () -> Unit,
    onDelete: () -> Unit,
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "",
                ) },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                actions = {
                    taskViewModel.selectedTaskState.value?.let {
                        IconButton(onClick = {
                            onDelete()
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
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {

            val taskName = remember { mutableStateOf(TextFieldValue("")) }
            val colorPicker = remember { mutableStateOf(false) }
            val colorSelected = remember{ mutableStateOf("") }
            val durationPicker = remember { mutableStateOf(false) }
            val duration = remember{ mutableStateOf("") }

            OutlinedTextField(
                maxLines = 1,
                value = taskName.value,
                onValueChange = { text -> taskName.value = text },
                placeholder = { Text(text = stringResource(id = R.string.task_name)) },
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            val text = taskName.value.text
                            taskName.value = taskName.value.copy(
                                selection = TextRange(0, text.length)
                            )
                        }
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(60.dp)
            )
            ConfigureItem(
                leadingIcon = R.drawable.ic_clock,
                label = R.string.length,
                onClick = {
                    durationPicker.value = true
                }) {
                Text(text = duration.value)
            }
            ConfigureItem(
                leadingIcon = R.drawable.ic_theme,
                label = R.string.theme,onClick = {
                    colorPicker.value = true
                }) {
                Theme(color = colorSelected.value)
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center

                ){
                    Text(
                        text = stringResource(id = R.string.cancel),
                        modifier = Modifier
                            .clickable { onCancel() }

                    )
                }
                Button(
                    onClick = { onDelete() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            }

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
                true -> {}
                false -> LocalContext.current.showToast(stringResource(id = R.string.invalid_duration))
            }
        }
    }
}