package com.example.trail2.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trail2.TodoData
import com.example.trail2.databinding.ItemCompletedTaskBinding
class CompletedAdapter: RecyclerView.Adapter<CompletedAdapter.CompletedViewHolder>() {
    private var completedList = listOf<TodoData>()
    inner class CompletedViewHolder(val binding: ItemCompletedTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TodoData) {
            Log.d("CompletedAdapter", "Binding: ${task.title}")
            binding.tv1TaskTitle.text=task.title
            binding.TaskDescription1.text=task.description
            binding.parentLayout1.setOnClickListener {
                toggleDescription(binding)
                binding.parentLayout1.cardElevation = if (binding.expandableLayout1.isExpanded) 8f else 0f
            }
        }
    }
    private fun toggleDescription(binding: ItemCompletedTaskBinding) {
        if (binding.expandableLayout1.isExpanded) {
            binding.expandableLayout1.collapse()
        } else {
            binding.expandableLayout1.expand()
        }
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
        Log.d("CompletedAdapter", "onBindViewHolder: ${completedList[position].title}")
        holder.bind(completedList[position])
    }
    override fun getItemCount(): Int = completedList.size
    fun submitList(newList: List<TodoData>) {
        Log.d("CompletedAdapter", "New completed list: ${newList.size}")
        completedList = newList.toList()
        notifyDataSetChanged()
    }
}