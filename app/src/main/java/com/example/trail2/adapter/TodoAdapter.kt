package com.example.trail2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trail2.TodoData
import com.example.trail2.databinding.ItemTodoTaskBinding
import java.util.Random

class TodoAdapter(
    private val onDeleteClick: (TodoData) -> Unit,
    private val onCheckChanged: (TodoData) -> Unit,
    private val onEditClick: (TodoData) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var taskList = listOf<TodoData>()
    inner class TodoViewHolder(val binding: ItemTodoTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TodoData) {
            binding.tvTaskTitle.text = task.title
            binding.TaskDescription.text = task.description
            binding.cbTaskComplete.setOnCheckedChangeListener(null)
            binding.cbTaskComplete.isChecked = task.isCompleted
            binding.cbTaskComplete.setOnCheckedChangeListener { _, isChecked ->
                val updatedTask = task.copy(isCompleted = isChecked)
                onCheckChanged(updatedTask)
            }
            binding.parentLayout.setOnLongClickListener {
                onEditClick(task)
                true
            }
            binding.parentLayout.setOnClickListener {
                toggleDescription(binding)
                binding.parentLayout.cardElevation = if (binding.expandableLayout.isExpanded) 8f else 0f
            }
            val random = Random()
            val r = 100 + random.nextInt(156)
            val g = 100 + random.nextInt(156)
            val b = 100 + random.nextInt(156)
            val randomColor = android.graphics.Color.rgb(r, g, b)

            binding.cbTaskComplete.buttonTintList =
                android.content.res.ColorStateList.valueOf(randomColor)
        }
        private fun toggleDescription(binding: ItemTodoTaskBinding) {
            if (binding.expandableLayout.isExpanded) {
                binding.expandableLayout.collapse()
            } else {
                binding.expandableLayout.expand()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(taskList[position])
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
