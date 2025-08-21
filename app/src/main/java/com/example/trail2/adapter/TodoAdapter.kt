package com.example.trail2.adapter

import android.graphics.Color
import android.view.LayoutInflater
import androidx.recyclerview.widget.ItemTouchHelper
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
            binding.cbTaskComplete.setOnCheckedChangeListener(null)
            binding.cbTaskComplete.isChecked = task.isCompleted
            binding.cbTaskComplete.setOnCheckedChangeListener { _, isChecked ->
                val updatedTask=task.copy(isCompleted = isChecked)
                onCheckChanged(updatedTask)
            }
            binding.parentLayout.setOnLongClickListener { onEditClick(task)
            true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TodoViewHolder {
        val binding = ItemTodoTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(taskList[position])

        val random = java.util.Random()
        val r = 100 + random.nextInt(156)
        val g = 100 + random.nextInt(156)
        val b = 100 + random.nextInt(156)
        val randomColor = android.graphics.Color.rgb(r, g, b)
        if (position % 2 == 0) {
            holder.binding.cbTaskComplete.buttonTintList =
                android.content.res.ColorStateList.valueOf(randomColor)
        } else {
            val r2 = 100 + random.nextInt(156)
            val g2 = 100 + random.nextInt(156)
            val b2 = 100 + random.nextInt(156)
            val randomColor2 = android.graphics.Color.rgb(r2, g2, b2)

            holder.binding.cbTaskComplete.buttonTintList =
                android.content.res.ColorStateList.valueOf(randomColor2)
        }
    }
    override fun getItemCount(): Int = taskList.size
    fun submitList(newList: List<TodoData>) {
        taskList = newList
        notifyDataSetChanged()
    }
    fun getTaskAt(position: Int): TodoData {
        return taskList[position]
    }
}