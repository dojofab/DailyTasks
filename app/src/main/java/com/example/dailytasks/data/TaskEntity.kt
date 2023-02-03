package com.example.dailytasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailytasks.util.Constant

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Entity(tableName = Constant.TABLE_NAME)
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String,
    val length: Int,
    val theme: String
)