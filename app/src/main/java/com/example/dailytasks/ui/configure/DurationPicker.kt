package com.example.dailytasks.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dailytasks.R
import com.example.dailytasks.data.Duration
import com.example.dailytasks.ui.custom.ExposedDropdownMenu
import com.example.dailytasks.ui.custom.Section

/**
 * Created by Donn Fabian on 02-01-2023
 */

@Composable
fun DurationPickerDialog(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    onClose: () -> Unit,
    onDurationSet: (Duration) -> Unit){

    Dialog(
        onDismissRequest = { openDialogCustom.value = false},) {
        DurationPicker(
            modifier = modifier,
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
    onClose: () -> Unit,
    onDurationSet: (Duration) -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val items = arrayListOf(
            "hours",
            "minutes",
            "seconds"
        )

        val textFieldState = remember { mutableStateOf(TextFieldValue("")) }
        val duration = remember { mutableStateOf(Duration(unit= items[0])) }

        Text(
            modifier = Modifier
                .paddingFromBaseline(
                    top = 50.dp,),
            text = stringResource(id = R.string.duration),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(
                    top = 10.dp,
                    bottom = 30.dp),
            text = stringResource(id = R.string.duration_spiel),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Section(
            label = R.string.unit,
            horizontalPadding = 20.dp,
            verticalPadding = 10.dp
        ) {
            ExposedDropdownMenu(
                items = items,
                onItemSelected = {
                    duration.value.unit = it
                })
        }
        Section(
            label = R.string.value,
            horizontalPadding = 20.dp,
            verticalPadding = 10.dp
        ) {
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                value = textFieldState.value,
                onValueChange = {
                        text -> textFieldState.value = text
                                duration.value.value = text.text.toInt()},
                placeholder = { Text(text = stringResource(id = R.string.zero)) },
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            val text = textFieldState.value.text
                            textFieldState.value = textFieldState.value.copy(
                                selection = TextRange(0, text.length)
                            )
                        }
                    }
                    .fillMaxWidth()
                    .height(60.dp)
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
