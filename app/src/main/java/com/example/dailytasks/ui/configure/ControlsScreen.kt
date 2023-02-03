package com.example.dailytasks.ui.configure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dailytasks.R
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun ControlsScreen(
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    taskEntity: TaskEntity?,
    taskName: MutableState<String>,
    colorSelected: MutableState<String>,
    length: MutableState<Int>,
    onConfigureDone: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
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
                    .clickable {
                        taskViewModel.resetTask()
                        onConfigureDone()
                    }
            )
        }
        Button(
            enabled = taskName.value.isNotEmpty() && colorSelected.value.isNotEmpty() && length.value > 0,
            onClick = {
                //add or update
                taskEntity?.let {
                    taskViewModel.addOrUpdateTask(it.copy(
                        name = taskName.value,
                        theme = colorSelected.value,
                        length = length.value,

                    ))
                }?: run{
                    taskViewModel.addOrUpdateTask(
                        TaskEntity(
                            name = taskName.value,
                            theme = colorSelected.value,
                            length = length.value,
                        )
                    )
                }
                onConfigureDone()

            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}