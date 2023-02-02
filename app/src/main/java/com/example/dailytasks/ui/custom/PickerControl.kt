package com.example.dailytasks.ui.configure

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Created by Donn Fabian on 02-02-2023
 */

@Composable
fun PickerBottomButtons(
    modifier: Modifier = Modifier,
    @StringRes negativeButtonLabel: Int,
    @StringRes positiveButtonLabel: Int,
    onNegativeButtonClick: () -> Unit,
    onPositiveButtonClick: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(id = negativeButtonLabel),
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onNegativeButtonClick()
                }
        )
        Text(
            text = stringResource(id = positiveButtonLabel),
            modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onPositiveButtonClick()
                }
        )
    }
}