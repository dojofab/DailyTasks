package com.example.dailytasks.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dailytasks.R
import com.example.dailytasks.ui.custom.Section

/**
 * Created by Donn Fabian on 02-01-2023
 */

@Composable
fun DurationPickerDialog(
    modifier: Modifier = Modifier,
    length: Int?,
    openDialogCustom: MutableState<Boolean>,
    onClose: () -> Unit,
    onDurationSet: (Int) -> Unit){

    Dialog(
        onDismissRequest = { openDialogCustom.value = false},) {
        DurationPicker(
            modifier = modifier,
            length = length,
            onClose = {
                onClose()
            },
            onDurationSet = {
                onDurationSet(it)
            })
    }
}

@Composable
fun DurationPicker(
    modifier: Modifier = Modifier,
    length: Int?,
    onClose: () -> Unit,
    onDurationSet: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var defaultText = ""
        length?.let {
            defaultText = it.toString()
        }

        val textFieldState = remember { mutableStateOf(defaultText) }
        val duration = remember { mutableStateOf(0) }

        Text(
            modifier = Modifier
                .paddingFromBaseline(
                    top = 50.dp,
                ),
            text = stringResource(id = R.string.duration),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(
                    top = 10.dp,
                    bottom = 30.dp
                ),
            text = stringResource(id = R.string.duration_spiel),
            fontSize = 14.sp,
            color = Color.Gray
        )
        Section(
            label = R.string.value_in_minutes,
            horizontalPadding = 20.dp,
            verticalPadding = 10.dp
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                singleLine = true,
                value = textFieldState.value,
                onValueChange = { text ->
                    if(text.isNotEmpty()) {
                        val input = text.filter { it.isDigit() }
                        textFieldState.value = input
                        duration.value = input.toInt()
                    }
                },
                placeholder = { Text(text = stringResource(id = R.string.zero)) },
            )
        }
        PickerBottomButtons(
            negativeButtonLabel = R.string.cancel,
            positiveButtonLabel = R.string.select,
            onNegativeButtonClick = {
                onClose()
            },
            onPositiveButtonClick = {
                onDurationSet(duration.value)
            }
        )
    }
}
