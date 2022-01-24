package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentHomeBinding



private lateinit var sqLiteHelper: SQLiteHelper
<<<<<<< HEAD
private lateinit var recyclerView: RecyclerView
//private var adapter: ListAdapter? = null
=======
>>>>>>> master

class HomeFragment : Fragment() {

    private lateinit var name: EditText
    private lateinit var reward: EditText

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
<<<<<<< HEAD
        val containerContext = container?.context

        val task1 = TodolistItem("hello")
        binding.recyclerview.adapter = TodolistAdapter()
=======
        binding.recyclerview.adapter = TodolistAdapter(this, getItemsList(container))
>>>>>>> master
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
        binding.textviewDate.text = getDate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
<<<<<<< HEAD
    private fun initView(){
       // binding.textField
      //  binding.rewardField
=======
>>>>>>> master

    private fun getItemsList(container: ViewGroup
    ?): ArrayList<TodolistsModelDB>{
        val containerContext = container?.context
        val sqliteHelper: SQLiteHelper = SQLiteHelper(containerContext!!)
        val empList: ArrayList<TodolistsModelDB> = sqliteHelper.getAllList()

<<<<<<< HEAD
    private fun initRecyclerView(){
      //  binding.recyclerview.layoutManager = LinearLayoutManager(containerContext!!)
=======
        return empList
>>>>>>> master
    }
//    private fun initView(){
//        binding.textField
//        binding.rewardField
//    }

//    private fun initRecyclerView(){
//        binding.recyclerview.layoutManager = LinearLayoutManager(containerContext!!)
//    }

}