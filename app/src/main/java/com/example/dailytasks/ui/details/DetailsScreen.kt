package com.example.dailytasks.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.R
import com.example.dailytasks.ui.custom.PagerIndicator
import com.example.dailytasks.viewmodel.TaskViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onEdit: () -> Unit,
    onBack: () -> Unit
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar (
                onBack = { onBack() },
                onEdit = { onEdit() }
            )
        }
    ) { contentPadding ->
        Content(
            modifier = Modifier.padding(contentPadding),
            taskViewModel = taskViewModel
        )
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
    onBack: () -> Unit
){
    TopAppBar(
        modifier = modifier,
        title = { Text(
            text = stringResource(id = R.string.app_name),
            color = Color.Blue,
        ) },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable {
                        onEdit()
                    },
                text = stringResource(id = R.string.edit),
                color = Color.Blue,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBack()
            }) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Content(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        val taskListState = taskViewModel.tasksListFlow.collectAsState(initial = listOf())
        val taskEntity = taskViewModel.selectedTaskState.value

        val selectedItemIndex = taskListState.value.indexOf(taskEntity)

        val coroutineScope = rememberCoroutineScope()

        val pagerState = rememberPagerState()
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            count = taskListState.value.size,
            state = pagerState
        ) { page ->
            StatefulTaskScreen(
                taskEntity = taskListState.value[page],
            )
        }
        PagerIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            totalDots = taskListState.value.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = Color.DarkGray,
            unSelectedColor = Color.LightGray
        )

        LaunchedEffect(key1 = pagerState.pageCount) {
            coroutineScope.launch {
                if (pagerState.pageCount != 0)
                    pagerState.animateScrollToPage(selectedItemIndex)
            }
        }
    }
}