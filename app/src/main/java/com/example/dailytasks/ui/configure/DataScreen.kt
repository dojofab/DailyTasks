package com.example.dailytasks.ui.configure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dailytasks.R
import com.example.dailytasks.util.displayDuration

/**
 * Created by Donn Fabian on 02-03-2023
 */

@Composable
fun DataScreen(
    modifier: Modifier,
    taskName: MutableState<String>,
    colorSelected: MutableState<String>,
    unit: MutableState<String>,
    length: MutableState<Int>,
    durationPicker: MutableState<Boolean>,
    colorPicker: MutableState<Boolean>,
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(60.dp),
            value = taskName.value,
            onValueChange = { text -> taskName.value = text },
            placeholder = { Text(text = stringResource(id = R.string.task_name)) },
            maxLines = 1,
            singleLine = true,
        )

        ConfigureItem(
            leadingIcon = R.drawable.ic_clock,
            label = R.string.length,
            onClick = {
                durationPicker.value = true
            }) {
            Text(text = length.value.displayDuration(unit.value))
        }
        ConfigureItem(
            leadingIcon = R.drawable.ic_theme,
            label = R.string.theme,onClick = {
                colorPicker.value = true
            }) {
            Theme(color = colorSelected.value)
        }
    }
}