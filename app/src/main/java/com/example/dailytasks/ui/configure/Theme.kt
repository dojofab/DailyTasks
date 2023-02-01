package com.example.dailytasks.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun Theme(
    modifier: Modifier = Modifier,
    color: Int
){
    Box(
        modifier = modifier
            .size(30.dp)
            .background(Color.Yellow)
            .padding(10.dp)
    ){

    }
}