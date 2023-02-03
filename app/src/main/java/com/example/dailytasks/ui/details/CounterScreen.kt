package com.example.dailytasks.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun CounterScreen(
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