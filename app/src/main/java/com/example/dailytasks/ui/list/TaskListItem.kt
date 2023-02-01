package com.example.dailytasks.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    task: TaskEntity,
    onClick: () -> Unit,
) {


    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = task.name,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = task.length.toString(),
        )
        Image(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = stringResource(id = R.string.clock_icon)
        )
        Image(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = stringResource(id = R.string.arrow_forward)
        )
    }
}

