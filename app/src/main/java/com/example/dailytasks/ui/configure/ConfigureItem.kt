package com.example.dailytasks.ui.configure

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun ConfigureItem(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .border(
                border = BorderStroke(width = 1.dp, Color.LightGray),
                shape = RoundedCornerShape(3.dp)
            )
            .height(60.dp)
            .clickable {
                onClick()
            }

    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp),
            painter = painterResource(id = leadingIcon),
            contentDescription = null
        )
        Text(
            text = stringResource(id = label),
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}