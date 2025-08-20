package com.example.trail2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoData (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val description: String,
    val isCompleted: Boolean
)