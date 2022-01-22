package com.example.ccl3

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentNewlistBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class NewListFragment : Fragment(R.layout.fragment_newlist) {

    private lateinit var binding: FragmentNewlistBinding

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

        dateRangePicker.show( childFragmentManager, "tag")

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.textviewTimeframe.text = "${convertLongToTime(startDate)} - ${convertLongToTime(endDate)}".toEditable()
            }
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