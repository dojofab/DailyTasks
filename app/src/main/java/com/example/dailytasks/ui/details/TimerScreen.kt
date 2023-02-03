package com.example.dailytasks.ui.details

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.R
import com.example.dailytasks.data.TaskEntity

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun StatefulTimerScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    timeStatus: MutableState<TaskScreenEnum>
){

    val timer = (taskEntity.length * 60000).toLong()

    //set timer value
    val millisInFuture = rememberSaveable {
        mutableStateOf(timer)
    }

    val timeData = rememberSaveable{
        mutableStateOf(timer)
    }

    val countDownTimer =
        object : CountDownTimer(millisInFuture.value, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //to get last time during pause
                millisInFuture.value = millisUntilFinished

                timeData.value = millisUntilFinished
            }

            override fun onFinish() {
                //reset value to the default time set
                millisInFuture.value = timer
                timeData.value = timer
            }
        }

    DisposableEffect(key1 = timeStatus.value) {
        when(timeStatus.value){
            TaskScreenEnum.PLAY -> countDownTimer.start()
            TaskScreenEnum.PAUSE -> countDownTimer.cancel()
            TaskScreenEnum.STOP -> countDownTimer.onFinish()
        }
        onDispose {
            countDownTimer.cancel()
        }
    }

    StatelessTimerScreen(
        modifier = modifier,
        taskEntity = taskEntity,
        timeData =timeData
    )
}

@Composable
fun StatelessTimerScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    timeData: MutableState<Long>,
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = taskEntity.name,
            fontSize = 30.sp
        )
        Timer(timeData = timeData)
    }
}

@Composable()
fun Timer(
    modifier: Modifier = Modifier,
    timeData: MutableState<Long>
){
    val seconds = (timeData.value / 1000) % 60
    val minutes = (timeData.value / (1000 * 60) % 60)
    val hours = (timeData.value / (1000 * 60 * 60) % 24)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        if(hours > 0) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "%02d".format(hours),
                    fontSize = 70.sp
                )
                Text(
                    text = stringResource(id = R.string.hours),
                )
            }
            TimerDividerScreen(modifier = Modifier.padding(horizontal = 10.dp))
        }

        if(minutes > 0 || hours > 0) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "%02d".format(minutes),
                    fontSize = 70.sp
                )
                Text(
                    text = stringResource(id = R.string.minutes),
                )
            }
            TimerDividerScreen(modifier = Modifier.padding(horizontal = 10.dp))
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "%02d".format(seconds),
                fontSize = 70.sp
            )
            Text(
                text = stringResource(id = R.string.seconds),
            )
        }
    }
}