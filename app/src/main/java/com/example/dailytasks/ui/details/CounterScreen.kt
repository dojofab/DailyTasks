package com.example.dailytasks.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    timeRemaining: MutableState<Long>,
    elapsedTime: MutableState<Long>,
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        CounterMinutesElapsed(
            modifier = modifier,
            time = elapsedTime
        )
        Spacer(modifier = Modifier.weight(1f))
        CounterMinutesRemaining(
            modifier = modifier,
            time = timeRemaining
        )
    }
}

@Composable
fun CounterMinutesElapsed(
    modifier: Modifier,
    time: MutableState<Long>
){
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.minutes_elapsed),
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.ic_sand_timer),
                contentDescription = null
            )
            Text(
                text = time.value.toString()
            )
        }
    }
}

@Composable
fun CounterMinutesRemaining(
    modifier: Modifier,
    time: MutableState<Long>
){
    Column(
        modifier = modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = stringResource(id = R.string.minutes_remaining),
            fontSize = 12.sp
        )
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = time.value.toString()
            )
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.ic_sand_timer),
                contentDescription = null
            )
        }
    }
}


