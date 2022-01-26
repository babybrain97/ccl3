package com.example.ccl3

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.FragmentTabTaskBinding



class TabTaskFragment : Fragment() {
    private var taskAdapter: TaskAdapter? = null

    private var _binding: FragmentTabTaskBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabTaskBinding.inflate(inflater, container, false)

//        binding.recyclerviewTasks.adapter = TaskAdapter(items = )
        // Inflate the layout for this fragment
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTasks()
    }

    private fun getTasks(): ArrayList<TasksModelDB>{
        val sqliteTaskHelper: SQLiteTaskHelper = SQLiteTaskHelper(requireContext())
        val taskList: ArrayList<TasksModelDB> = sqliteTaskHelper.getAllTask()


        val taskListAdapter = TaskAdapter(taskList)
        binding.recyclerviewTasks.adapter = taskListAdapter

        val swipeDelete = object : SwipeDeleteCallback(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                taskListAdapter.deleteItem(viewHolder.absoluteAdapterPosition)
            }
        }
        val touchHelper = ItemTouchHelper(swipeDelete)
        touchHelper.attachToRecyclerView(binding.recyclerviewTasks)

        return taskList
    }
}