package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ccl3.databinding.FragmentTodolistBinding
import com.google.android.material.tabs.TabLayoutMediator

class TodolistFragment : Fragment() {


    private var _binding: FragmentTodolistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)

        //Task Items
       // binding.re = TaskAdapter(this, getItemTask(container))

        //Tab Layout
        binding.viewPager2.adapter = ViewTabAdapter(parentFragmentManager,lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab,position ->
            when(position){
                0->{tab.text="Tasks"}
                1->{tab.text="Insights"}
            }
        }.attach()

        return binding.root

    }

    val args: TodolistFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = args.title
        binding.textviewListName.text = title
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_TodolistFragment_to_NewTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getItemTask(container: ViewGroup ?): ArrayList<TasksModelDB> {
        val containerContext = container?.context
        val sqliteTaskHelper: SQLiteTaskHelper = SQLiteTaskHelper(containerContext!!)
        val empTask: ArrayList<TasksModelDB> = sqliteTaskHelper.getAllTask()

        return empTask
    }

}
