package com.example.dailytasks.repository

import com.example.dailytasks.data.TaskDao
import com.example.dailytasks.data.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Donn Fabian on 02-01-2023
 */
interface TaskRepositoryAbstract{
    fun getTasks(): Flow<List<TaskEntity>>
    suspend fun insert(task: TaskEntity)
    suspend fun update(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
}


class TaskRepository(private val taskDao: TaskDao): TaskRepositoryAbstract{

    override fun getTasks(): Flow<List<TaskEntity>> {
        return taskDao.getTasks()
    }

    override suspend fun insert(task: TaskEntity) {
        taskDao.insert(task = task)
    }

    override suspend fun update(task: TaskEntity) {
        taskDao.update(task = task)
    }

    override suspend fun delete(task: TaskEntity) {
        taskDao.delete(task = task)
    }
}