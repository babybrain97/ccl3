package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.FragmentHomeBinding
import com.example.ccl3.databinding.FragmentNewlistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
private lateinit var name: EditText
private lateinit var reward: EditText

private lateinit var sqLiteHelper: SQLiteHelper
private lateinit var recyclerView: RecyclerView
//private var adapter: ListAdapter? = null

private lateinit var binding: FragmentNewlistBinding

class HomeFragment : Fragment(R.layout.fragment_newlist) {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val containerContext = container?.context

        val task1 = TodolistItem("hello")
        binding.recyclerview.adapter = TodolistAdapter()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_TodolistFragment)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_NewlistFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initView(){
       // binding.textField
      //  binding.rewardField

    }

    private fun initRecyclerView(){
      //  binding.recyclerview.layoutManager = LinearLayoutManager(containerContext!!)
    }

}