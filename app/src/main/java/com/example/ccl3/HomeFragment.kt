package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentHomeBinding



private lateinit var sqLiteHelper: SQLiteHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_NewlistFragment)
        }
        binding.textviewDate.text = getDate()
        getItemsData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getItemsData(): ArrayList<TodolistsModelDB>{
        val sqliteHelper: SQLiteHelper = SQLiteHelper(requireContext())
        val empList: ArrayList<TodolistsModelDB> = sqliteHelper.getAllList()

        val todolistAdapter = TodolistAdapter(empList, this::onItemClicked)
        binding.recyclerview.adapter = todolistAdapter
        return empList
    }

    private fun onItemClicked(item: TodolistsModelDB){
        Toast.makeText(requireContext(), "${item.name} + was clicked ", Toast.LENGTH_SHORT)
            .show()

        val title = item.name
        val id = item.id
        val action = HomeFragmentDirections.actionHomeFragmentToTodolistFragment(
            title,
            id
        )
        findNavController().navigate(action)
    }
}
