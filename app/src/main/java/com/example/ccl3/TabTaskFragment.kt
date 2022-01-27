package com.example.ccl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.ccl3.databinding.FragmentTabTaskBinding



class TabTaskFragment : Fragment() {

    private var _binding: FragmentTabTaskBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val DATA_KEY = "DATA_KEY"
        fun newInstance(data: Int): TabTaskFragment {
            val fragment = TabTaskFragment()
            fragment.arguments = bundleOf((DATA_KEY to data))
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTabTaskBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listId = requireArguments().getInt(DATA_KEY)
        super.onViewCreated(view, savedInstanceState)
        getTasks(listId)
//        getTasks()
    }

    private fun getTasks(id: Int): ArrayList<TasksModelDB>{
        val sqliteTaskHelper: SQLiteTaskHelper = SQLiteTaskHelper(requireContext())
//        val taskList: ArrayList<TasksModelDB> = sqliteTaskHelper.getAllTask()
        val taskList: ArrayList<TasksModelDB> = sqliteTaskHelper.getTasksfromList(id)
        val taskListAdapter = TaskAdapter(taskList)

        binding.recyclerviewTasks.adapter = taskListAdapter
        return taskList
    }
}