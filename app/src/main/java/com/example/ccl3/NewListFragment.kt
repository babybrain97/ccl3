package com.example.ccl3


import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccl3.databinding.FragmentNewlistBinding

class NewListFragment : Fragment(R.layout.fragment_newlist) {

    private lateinit var name: EditText
    private lateinit var reward: EditText
   // private lateinit var btnSave: Button

    private lateinit var sqliteHelper: SQLiteHelper

    private lateinit var binding: FragmentNewlistBinding



    // This property is only valid between onCreateView and
    // onDestroyView.
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
           val name = binding.textField.editText.toString()
           val reward = binding.rewardField.editText.toString()

        if (name.isEmpty() || reward.isEmpty()) {


            Toast.makeText(context, "Please enter ", Toast.LENGTH_SHORT).show()

        }else{
            val std = ListModelDb(name = name, reward = reward)
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


        binding.saveListButton.setOnClickListener { saveList() }
        }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this.toString())
}