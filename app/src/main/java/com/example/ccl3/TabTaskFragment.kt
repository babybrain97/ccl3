package com.example.ccl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ccl3.databinding.FragmentTabTaskBinding



class TabTaskFragment : Fragment() {
    private var taskAdapter: TaskAdapter? = null

    private var _binding: FragmentTabTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTabTaskBinding.inflate(inflater, container, false)
        binding.recyclerviewTasks.adapter = taskAdapter

        getTask(container)
        // Inflate the layout for this fragment
       return binding.root
    }

    private fun getTask(container: ViewGroup ?): ArrayList<TasksModelDB> {
        val containerContext = container?.context
        val sqliteTaskHelper: SQLiteTaskHelper = SQLiteTaskHelper(containerContext!!)
        val taskList: ArrayList<TasksModelDB> = sqliteTaskHelper.getAllTask()

        return taskList
    }
}