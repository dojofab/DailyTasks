package com.example.dailytasks.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-03-2023
 */
@Composable
fun ControlsScreen(
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