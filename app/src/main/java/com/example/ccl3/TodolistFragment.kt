package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ccl3.databinding.FragmentTodolistBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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

        binding.viewPager2.adapter = ViewTabAdapter(parentFragmentManager,lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab,position ->
            when(position){
                0->{tab.text="Tasks"}
                1->{tab.text="Insights"}
            }
        }.attach()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_TodolistFragment_to_NewTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}