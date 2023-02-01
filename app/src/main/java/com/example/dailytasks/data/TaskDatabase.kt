package com.example.dailytasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailytasks.util.Constant

/**
 * Created by Donn Fabian on 02-01-2023
 */
@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var instance_: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {

            val tempInstance = instance_
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    Constant.TABLE_NAME
                ).build()
                instance_ = instance
                return instance
            }

        }
    }
}