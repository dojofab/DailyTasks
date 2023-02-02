package com.example.dailytasks.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Donn Fabian on 02-02-2023
 */

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}