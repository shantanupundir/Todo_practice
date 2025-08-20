package com.example.trail2.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trail2.TodoData
import com.example.trail2.databinding.ItemCompletedTaskBinding

class CompletedAdapter(private val onDeleteClick: (TodoData) -> Unit): RecyclerView.Adapter<CompletedAdapter.CompletedViewHolder>() {
    private var completedList = listOf<TodoData>()

    inner class CompletedViewHolder(val binding: ItemCompletedTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TodoData) {
            Log.d("CompletedAdapter", "Binding: ${task.title}")
            binding.tv1TaskTitle.text=task.title
            binding.tv1TaskDescription.text=task.description
            binding.btn1DeleteTask.setOnClickListener { onDeleteClick(task) }
        }
    }
    private fun getRandomColor(): Int {
        val random = java.util.Random()
        val r = 200 + random.nextInt(56)
        val g = 200 + random.nextInt(56)
        val b = 200 + random.nextInt(56)
        return Color.rgb(r, g, b)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedViewHolder {
        val binding = ItemCompletedTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CompletedViewHolder(binding)
    }

    override fun onBindViewHolder(holder:CompletedViewHolder, position: Int) {
        holder.bind(completedList[position])
        if (position % 2 == 0) {
            holder.binding.parentLayout1.setBackgroundColor(getRandomColor())
        } else {
            holder.binding.parentLayout1.setBackgroundColor(getRandomColor())
        }
    }

    override fun getItemCount(): Int = completedList.size

    fun submitList(newList: List<TodoData>) {
        Log.d("CompletedAdapter", "New completed list: ${newList.size}")
        completedList = newList.toList()
        notifyDataSetChanged()
    }
}