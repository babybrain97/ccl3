package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.ccl3.databinding.FragmentTodolistBinding
import com.google.android.material.tabs.TabLayoutMediator

class TodolistFragment : Fragment() {


    private var _binding: FragmentTodolistBinding? = null
    private val binding get() = _binding!!

    private var progress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)


        binding.boxCheck.setOnClickListener {v ->
            if (binding.boxCheck.isChecked){
                progress +=10
                updateProgressBar()
            }else{
                progress -=10
                updateProgressBar()
            }

        }


        val fragment = TabTaskFragment.newInstance(args.id)
        val fragment2 = TabTaskFragment.newInstance(args.id)

        val fragments = listOf(fragment, fragment2)
        //Tab Layout
        binding.viewPager2.adapter = ViewTabAdapter(childFragmentManager,lifecycle, fragments)

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
//        val action = TodolistFragmentDirections(
//            title,
//            id
//        )
//        findNavController().navigate(action)

//

        val arg: TodolistFragmentArgs by navArgs()
        binding.fab.setOnClickListener{

            val id = args.id
            val action = TodolistFragmentDirections.actionTodolistFragmentToNewTaskFragment(
                id
            )
            findNavController().navigate(action)

//            findNavController().navigate(R.id.action_TodolistFragment_to_NewTaskFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateProgressBar(){
        binding.progressBar.setProgress(progress)
    }
}
