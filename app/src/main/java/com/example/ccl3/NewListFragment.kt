package com.example.ccl3

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentNewlistBinding

class NewListFragment : Fragment(R.layout.fragment_newlist) {

    private lateinit var binding: FragmentNewlistBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewlistBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveListButton.setOnClickListener {
            findNavController().navigate(R.id.action_TodolistFragment_to_NewTaskFragment)
            }
        }
}