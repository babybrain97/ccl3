package com.example.ccl3

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentNewlistBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class NewListFragment : Fragment(R.layout.fragment_newlist) {

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var binding: FragmentNewlistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewlistBinding.inflate(inflater, container, false)
        val containerContext = container?.context
        initView()
        sqliteHelper = SQLiteHelper(containerContext!!)
        return binding.root
    }


    private fun saveList(){
        val name = binding.listTitle.text.toString()
        val reward = binding.rewardText.text.toString()

        if (name.isEmpty() || reward.isEmpty()) {

            Toast.makeText(context, "Please enter ", Toast.LENGTH_SHORT).show()

        }else{
            val std = TodolistsModelDB(name = name, reward = reward)
            val status = sqliteHelper.insertList(std)

            //Check insert success or not success
            if ( status > -1){
                Toast.makeText(context, "List added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else{
                Toast.makeText(context, "Record not saved ", Toast.LENGTH_SHORT).show()
            }
        }

        val stdList = sqliteHelper.getAllList()
        Log.e("pppp", "${stdList.size}")
    }

    private fun clearEditText(){
        var str=""
        binding.textField.editText?.text = str.toEditable()
        binding.rewardField.editText?.text = str.toEditable()
        binding.textField.requestFocus()
    }

    private fun initView(){
        binding.textField
        binding.rewardField

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveListButton.setOnClickListener {
            findNavController().navigate(R.id.action_NewlistFragment_to_HomeFragment)
            }
        binding.textviewTimeframe.text = "${getDate()}".toEditable()
        binding.textviewTimeframe.setOnClickListener{
            showDateRangePicker()
        }
        binding.changeIntentionBtn.setOnClickListener {
            findNavController().navigate(R.id.action_NewlistFragment_to_SetIntentionFragment)
        }

    }

    private fun showDateRangePicker() {

        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
            .setTitleText("Select dates")
            .build()

        dateRangePicker.show( parentFragmentManager, "timeframe")

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.textviewTimeframe.text = "${convertLongToTime(startDate)} - ${convertLongToTime(endDate)}".toEditable()
            }

        binding.saveListButton.setOnClickListener { saveList() }
        }
    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault())
        return format.format(date)
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this.toString())
}