package com.example.dailytasks.util

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

/**
 * Created by Donn Fabian on 02-02-2023
 */

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Int.displayDuration(): String{
    val unit = "minutes"
    return when (this) {
        0 -> ""
        1 -> "$this ${unit.dropLast(1)}"
        else -> "$this $unit"
    }
}

fun String.formatColor(): Color{
    return if(!this.contains("#"))
        Color("#${this}".toColorInt())
    else
        Color(this.toColorInt())
}