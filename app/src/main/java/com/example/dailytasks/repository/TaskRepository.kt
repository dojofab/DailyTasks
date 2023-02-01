package com.example.dailytasks.repository

import com.example.dailytasks.data.TaskDao
import com.example.dailytasks.data.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Donn Fabian on 02-01-2023
 */
class TaskRepository(
    private val taskDao: TaskDao
) {

    fun getTasks(): Flow<List<TaskEntity>> = taskDao.getTasks()
    suspend fun insert(task: TaskEntity) = taskDao.insert(task = task)
    suspend fun update(task: TaskEntity) = taskDao.update(task = task)
    suspend fun delete(task: TaskEntity) = taskDao.delete(task = task)
}