package com.example.trail2

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoData::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao


    companion object{
        @Volatile
        private var instan: TodoDatabase?=null
        fun getDB(context: Context): TodoDatabase{
            return instan ?: synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java,"todo_database"
                ).build()
                instan=instance
                instance
            }
        }
    }
}