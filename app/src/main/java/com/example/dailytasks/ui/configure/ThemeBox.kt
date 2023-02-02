package com.example.dailytasks.ui.configure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Composable
fun Theme(
    modifier: Modifier = Modifier,
    color: String
){
    if(color.isNotEmpty()){

        var formatColor = color
        if(!formatColor.contains("#"))
            formatColor = "#$formatColor"

        Box(
            modifier = modifier
                .size(30.dp)
                .background(Color(formatColor.toColorInt()))
                .padding(10.dp)
        ){

        }
    }
}