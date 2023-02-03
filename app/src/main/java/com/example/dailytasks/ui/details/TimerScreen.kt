package com.example.dailytasks.ui.details

import android.os.CountDownTimer
import androidx.annotation.StringRes
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
import java.util.concurrent.TimeUnit

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun StatefulTimerScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    timeStatus: MutableState<TaskScreenEnum>,
    timeRemaining: MutableState<Long>,
    elapsedTime: MutableState<Long>,
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
        originalTime = timer,
        timeData =timeData,
        timeRemaining = timeRemaining,
        elapsedTime = elapsedTime
    )
}

@Composable
fun StatelessTimerScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    originalTime: Long,
    timeData: MutableState<Long>,
    timeRemaining: MutableState<Long>,
    elapsedTime: MutableState<Long>,
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
        Timer(
            timeData = timeData,
            originalTime = originalTime,
            elapsedTime = elapsedTime,
            timeRemaining = timeRemaining
        )
    }
}

@Composable()
fun Timer(
    modifier: Modifier = Modifier,
    originalTime: Long,
    timeData: MutableState<Long>,
    timeRemaining: MutableState<Long>,
    elapsedTime: MutableState<Long>,
){

    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeData.value)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeData.value) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeData.value))

    timeRemaining.value = minutes
    elapsedTime.value = TimeUnit.MILLISECONDS.toMinutes(originalTime - timeData.value)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){

        Time(
            modifier = Modifier,
            value = minutes,
            label = R.string.minutes
        )
        TimerDividerScreen(modifier = Modifier.padding(horizontal = 10.dp))
        Time(
            modifier = Modifier,
            value = seconds,
            label = R.string.seconds
        )
    }
}

@Composable
fun Time(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    value: Long,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "%02d".format(value),
            fontSize = 70.sp
        )
        Text(
            text = stringResource(id = label),
        )
    }
}
