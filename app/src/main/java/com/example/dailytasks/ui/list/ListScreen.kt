package com.example.dailytasks.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailytasks.ui.theme.DailyTasksTheme
import com.example.dailytasks.R
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onTaskClick: () -> Unit,
    onConfigure: () -> Unit,
){

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                taskViewModel = taskViewModel
            ) {
                onConfigure()
            }
        }
    ) { contentPadding ->

        val taskListState = taskViewModel.tasksListFlow.collectAsState(initial = null)

        Content(
            modifier = Modifier.padding(contentPadding),
            tasks = taskListState.value,
        ) {
            taskViewModel.selectTask(it)
            onTaskClick()
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onConfigure: () -> Unit,
){
    TopAppBar(
        modifier = modifier,
        title = { Text(
            text = stringResource(id = R.string.app_name),
        ) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                taskViewModel.resetTask()
                onConfigure()
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription =  stringResource(id = R.string.add_button),
                    tint = Color.Blue
                )
            }
        }
    )
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    tasks: List<TaskEntity>?,
    onTaskClick: (TaskEntity) -> Unit
){

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        tasks?.let { it ->
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 10.dp)
            ){
                items(
                    items = it,
                    key = { it.id?: 0 }
                ){
                    TaskListItem(
                        task = it,
                        onClick = {
                            onTaskClick(it)
                        }
                    )
                }
            }

            if(it.isEmpty()){
                Text(
                    text = stringResource(id = R.string.no_available_task),
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    DailyTasksTheme {

    }
}