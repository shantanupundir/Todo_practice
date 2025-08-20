package com.example.trail2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.trail2.Model.TodoViewModel
import com.example.trail2.R
import com.example.trail2.TodoData
import com.example.trail2.TodoDatabase
import com.example.trail2.adapter.TodoAdapter
import com.example.trail2.databinding.AddItemDialogBinding
import com.example.trail2.databinding.FragmentTodoBinding


class TodoFragment : Fragment() {
    private var fragBinding: FragmentTodoBinding?=null
    private val binding get() = fragBinding!!
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragBinding= FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = TodoDatabase.getDB(requireContext()).todoDao()
        viewModel= ViewModelProvider(this)[TodoViewModel::class.java]

        adapter= TodoAdapter(
            onDeleteClick = { task -> viewModel.delete(task) },
            onCheckChanged = { updatedTask ->
                viewModel.updateTask(updatedTask)
            },
            onEditClick ={ task -> showEditDialog(task) }
        )

        binding.recyclerViewTodo.adapter = adapter

        viewModel.allTasks.observe(viewLifecycleOwner) { allTasks ->
            val pendingTasks = allTasks.filter { !it.isCompleted }
            adapter.submitList(pendingTasks)

            if (pendingTasks.isEmpty()) {
                Toast.makeText(requireContext(), "No tasks available", Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragBinding = null
    }
    private fun showEditDialog(task: TodoData) {
        val dialogBinding = AddItemDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogBinding.root).setCancelable(true).create()

        dialogBinding.titleEdit.setText(task.title)
        dialogBinding.descriptionEdit.setText(task.description)
        dialogBinding.btnAdd.text = "Update"
        dialogBinding.btnCancel.setOnClickListener { dialog.dismiss() }
        dialogBinding.btnAdd.setOnClickListener {
            val updatedTitle = dialogBinding.titleEdit.text.toString().trim()
            val updatedDesc = dialogBinding.descriptionEdit.text.toString().trim()
            if (updatedTitle.isEmpty()) {
                dialogBinding.titleEdit.error = "Title cannot be empty"
            } else {
                val updatedTask = task.copy(title = updatedTitle, description = updatedDesc)
                viewModel.updateTask(updatedTask)
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}