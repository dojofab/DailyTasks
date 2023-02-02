package com.example.dailytasks.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.*
import com.example.dailytasks.R

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun ColorPickerDialog(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    onColorSelected: (String) -> Unit){

    Dialog(onDismissRequest = { openDialogCustom.value = false}) {
        ColorPicker(
            modifier = modifier,
            onColorSelected = {
            onColorSelected(it)
        })
    }
}

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    onColorSelected: (String) -> Unit
) {
    val controller = rememberColorPickerController()

    val colorSelected = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(20.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                //onColorSelected(it.hexCode)
                colorSelected.value = it.hexCode
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
        )
        PickerBottomButtons(
            negativeButtonLabel = R.string.cancel,
            positiveButtonLabel = R.string.select,
            onNegativeButtonClick = { onColorSelected("") },
            onPositiveButtonClick = { onColorSelected(colorSelected.value) }
        )
    }
}



