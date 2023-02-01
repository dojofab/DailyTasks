package com.example.dailytasks.data

import androidx.room.*
import com.example.dailytasks.util.Constant
import kotlinx.coroutines.flow.Flow

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM ${Constant.TABLE_NAME}")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insert(task: TaskEntity)

    @Update
    suspend fun update(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}