package com.example.trail2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getAll(): LiveData<List<TodoData>>

    @Query("SELECT * FROM todo_table WHERE isCompleted=1")
    fun getCompleted():LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TodoData)

    @Delete
    suspend fun delete(task: TodoData)

    @Update
    suspend fun update(task: TodoData)

}