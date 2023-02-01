package com.example.dailytasks.di

import android.app.Application
import com.example.dailytasks.data.TaskDao
import com.example.dailytasks.data.TaskDatabase
import com.example.dailytasks.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepository(taskDao = taskDao)
    }

    @Singleton
    @Provides
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return TaskDatabase.getDatabase(app)
    }

    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }
}