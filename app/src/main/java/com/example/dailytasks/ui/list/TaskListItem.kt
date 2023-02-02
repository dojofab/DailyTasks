package com.example.dailytasks.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailytasks.data.TaskEntity
import com.example.dailytasks.R
import com.example.dailytasks.util.displayLength
import com.example.dailytasks.util.formatColor

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
        modifier = modifier
            .background(task.theme.formatColor())
            .height(60.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = task.name,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            fontSize = 20.sp
        )
        Text(
            text = task.length.displayLength(task.unit),
        )
        Icon(
            modifier = Modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 5.dp
                ),
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = stringResource(id = R.string.clock_icon),

        )
        Icon(
            modifier = Modifier
                .padding(
                    vertical = 20.dp,
                    horizontal = 10.dp
                ),
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = stringResource(id = R.string.arrow_forward),
        )
    }
}

