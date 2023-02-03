package com.example.dailytasks.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.util.formatColor

/**
 * Created by Donn Fabian on 02-02-2023
 */
enum class TaskScreenEnum {
    PLAY,
    PAUSE,
    STOP
}

@Composable
fun StatefulTaskScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(taskEntity.theme.formatColor())

    ) {

        val timerStatus = rememberSaveable{ mutableStateOf(TaskScreenEnum.STOP) }
        val elapsedTime = rememberSaveable{ mutableStateOf(0L) }
        val timeRemaining = rememberSaveable{ mutableStateOf(0L) }

        StatelessTaskScreen(
            taskEntity = taskEntity,
            timerStatus = timerStatus,
            elapsedTime = elapsedTime,
            timeRemaining = timeRemaining
        )
    }
}

@Composable
fun StatelessTaskScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    timerStatus: MutableState<TaskScreenEnum>,
    timeRemaining: MutableState<Long>,
    elapsedTime: MutableState<Long>,
){
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(taskEntity.theme.formatColor())

    ){
        CounterScreen(
            modifier = Modifier,
            timeRemaining = timeRemaining,
            elapsedTime = elapsedTime
        )
        StatefulTimerScreen(
            modifier = Modifier.weight(1f),
            taskEntity = taskEntity,
            timeStatus = timerStatus,
            timeRemaining = timeRemaining,
            elapsedTime = elapsedTime
        )
        ControlsScreen(
            onPlay = { timerStatus.value = TaskScreenEnum.PLAY },
            onPause = {timerStatus.value = TaskScreenEnum.PAUSE },
            onStop = {timerStatus.value = TaskScreenEnum.STOP }
        )
    }
}

