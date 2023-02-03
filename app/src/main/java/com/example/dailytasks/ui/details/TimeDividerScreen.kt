package com.example.dailytasks.ui.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun TimerDividerScreen(
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.timer_divider),
        fontSize = 50.sp
    )
}