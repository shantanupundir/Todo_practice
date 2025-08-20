package com.example.trail2.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trail2.TodoData
import com.example.trail2.databinding.ItemTodoTaskBinding

class TodoAdapter (
    private val onDeleteClick:(TodoData)->Unit,
    private val onCheckChanged:(TodoData)->Unit,
    private val onEditClick:(TodoData)->Unit
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    private var taskList=listOf<TodoData>()

    inner class TodoViewHolder(val binding: ItemTodoTaskBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: TodoData){
            binding.tvTaskTitle.text=task.title
            binding.tvTaskDescription.text=task.description
            binding.cbTaskComplete.setOnCheckedChangeListener(null)
            binding.cbTaskComplete.isChecked = task.isCompleted
            binding.cbTaskComplete.setOnCheckedChangeListener { _, isChecked ->
                val updatedTask=task.copy(isCompleted = isChecked)
                onCheckChanged(updatedTask)
            }
            binding.btnDeleteTask.setOnClickListener { onDeleteClick(task) }
            binding.btnEditTask.setOnClickListener { onEditClick(task) }
        }
    }
    private fun getRandomColor(): Int {
        val random = java.util.Random()
        val r = 200 + random.nextInt(56)
        val g = 200 + random.nextInt(56)
        val b = 200 + random.nextInt(56)
        return Color.rgb(r, g, b)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TodoViewHolder {
        val binding = ItemTodoTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(taskList[position])
        if (position % 2 == 0) {
            holder.binding.parentLayout.setBackgroundColor(getRandomColor())
        } else {
            holder.binding.parentLayout.setBackgroundColor(getRandomColor())
        }
    }
    override fun getItemCount(): Int = taskList.size
    fun submitList(newList: List<TodoData>) {
        taskList = newList
        notifyDataSetChanged()
    }
}