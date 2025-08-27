package com.example.trail2.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trail2.TodoDao
import com.example.trail2.TodoData
import com.example.trail2.TodoDatabase
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: TodoDao = TodoDatabase
        .getDB(application)
        .todoDao()
    val allTasks: LiveData<List<TodoData>> = dao.getAll()
    val completedTasks: LiveData<List<TodoData>> = dao.getCompleted()
    fun insert(task: TodoData) = viewModelScope.launch {
        dao.insert(task)
    }
    fun delete(task: TodoData) = viewModelScope.launch {
        dao.delete(task)
    }
    fun updateTask(task: TodoData) = viewModelScope.launch {
        dao.update(task)
    }
}
