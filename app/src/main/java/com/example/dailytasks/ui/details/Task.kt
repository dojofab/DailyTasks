package com.example.dailytasks.ui.details

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.util.formatColor
import com.example.dailytasks.R
import com.example.dailytasks.viewmodel.TaskViewModel

/**
 * Created by Donn Fabian on 02-02-2023
 */
enum class TimerEnum{
    PAUSE,
    PLAY,
    STOP
}

@Composable
fun Task(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    taskViewModel: TaskViewModel
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(taskEntity.theme.formatColor())

    ) {

        val timerStatus = remember { mutableStateOf(TimerEnum.PAUSE) }

        Counter(
            modifier = Modifier
        )
        TimerScreen(
            modifier = Modifier.weight(1f),
            taskEntity = taskEntity,
            taskViewModel = taskViewModel,
            timerStatus = timerStatus
        )
        Controls(
            onPlay = { timerStatus.value = TimerEnum.PLAY },
            onPause = {timerStatus.value = TimerEnum.PAUSE },
            onStop = {timerStatus.value = TimerEnum.STOP }
        )
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.minutes_elapsed),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.minutes_remaining),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    taskEntity: TaskEntity,
    taskViewModel: TaskViewModel,
    timerStatus: MutableState<TimerEnum>
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        taskViewModel.setTimer(taskEntity.length, taskEntity.unit)
        val timer = taskViewModel.timer.value
        val millisInFuture: Long = timer * 1000

        val timeData = remember {
            mutableStateOf(millisInFuture)
        }

        val countDownTimer =
            object : CountDownTimer(millisInFuture, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.d("TAG", "onTick: ")
                    timeData.value = millisInFuture
                }

                override fun onFinish() {

                }
            }

        Text(
            text = taskEntity.name,
            fontSize = 30.sp
        )
        Timer(timeData = timeData)

        /*when(timerStatus.value){
            TimerEnum.PLAY -> { countDownTimer.start() }
            TimerEnum.STOP -> { countDownTimer.onFinish() }
            TimerEnum.PAUSE -> {countDownTimer.cancel() }
        }*/
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    timeData: MutableState<Long>,
){

    val secMilSec: Long = 1000
    val minMilSec = 60 * secMilSec
    val hourMilSec = 60 * minMilSec
    val dayMilSec = 24 * hourMilSec
    val hours = (timeData.value % dayMilSec / hourMilSec).toInt()
    val minutes = (timeData.value  % dayMilSec % hourMilSec / minMilSec).toInt()
    val seconds = (timeData.value  % dayMilSec % hourMilSec % minMilSec / secMilSec).toInt()
    
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
            TimerDivider(modifier = Modifier.padding(horizontal = 10.dp))
        }

        if(minutes > 0) {
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
            TimerDivider(modifier = Modifier.padding(horizontal = 10.dp))
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

@Composable
fun TimerDivider(
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.timer_divider),
        fontSize = 50.sp
    )
}

@Composable
fun Controls(
    modifier: Modifier = Modifier,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onStop: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clickable { onStop() },
            painter = painterResource(id = R.drawable.ic_stop),
            contentDescription = null,
            tint = Color.Red
        )
        Icon(
            modifier = Modifier
                .size(110.dp)
                .padding(horizontal = 10.dp)
                .clickable {
                    onPlay()
                },
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = null,
            tint = Color.Blue
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clickable { onPause() },
            painter = painterResource(id = R.drawable.ic_pause),
            contentDescription = null,
            tint = Color.LightGray
        )
    }
}