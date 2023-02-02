package com.example.dailytasks.ui.custom

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Donn Fabian on 02-02-2023
 */
@Composable
fun Section(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    horizontalPadding: Dp = 0.dp,
    verticalPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
    ) {
        Text(
            modifier = Modifier
                .paddingFromBaseline(bottom = 10.dp)
                .align(Alignment.Start),
            text = stringResource(id = label),
            fontSize = 14.sp,
        )
        content()
    }
}