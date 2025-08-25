package com.example.trail2.ui
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trail2.Model.TodoViewModel
import com.example.trail2.adapter.CompletedAdapter
import com.example.trail2.databinding.FragmentCompletedBinding
class CompletedFragment: Fragment(){
    private var fragBinding: FragmentCompletedBinding?=null
    private val binding get()=fragBinding!!
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: CompletedAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        fragBinding = FragmentCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[TodoViewModel::class.java]
        adapter = CompletedAdapter()
        binding.completedRecyclerView.adapter = adapter

        viewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            val completedTasks = tasks.filter { it.isCompleted }
            Log.d("FragmentCompleted", "Completed tasks count: ${completedTasks.size}")
            adapter.submitList(completedTasks)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragBinding = null
    }
}