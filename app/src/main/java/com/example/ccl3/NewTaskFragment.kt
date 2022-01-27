package com.example.ccl3

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ccl3.databinding.FragmentNewTaskBinding
import com.example.ccl3.databinding.FragmentNewlistBinding


class NewTaskFragment : Fragment(R.layout.fragment_new_task) {
//    private var _binding: FragmentNewTaskBinding? = null
//    private val binding get() = _binding!!

    private lateinit var sqlitetaskHelper: SQLiteTaskHelper
    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        sqlitetaskHelper = SQLiteTaskHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val title = args.title
//
//        val action = NewTaskFragmentDirections.actionNewTaskFragmentToTodolistFragment(title)
//        findNavController().navigate(action)

        binding.saveTaskButton.setOnClickListener {
            saveTask()
            findNavController().navigateUp()
        }
    }

    val arg: NewTaskFragmentArgs by navArgs()

    private fun saveTask() {
        val listId = arg.listId
        val taskTitle = binding.taskTitle.text.toString()
        val taskDescription = binding.taskDescription.text.toString()


        if (taskTitle.isEmpty() || taskDescription.isEmpty()) {

            Toast.makeText(context, "Please enter ", Toast.LENGTH_SHORT).show()

        } else {
            val std = TasksModelDB(
                nameTask = taskTitle,
                description = taskDescription,
                listId = listId,
                done = false
            )
            val status = sqlitetaskHelper.insertList(std)

            //Check insert success or not success
            if (status > -1) {
                Toast.makeText(context, "Task added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(context, "Task not saved ", Toast.LENGTH_SHORT).show()
            }
        }

        val stdList = sqlitetaskHelper.getAllTask()
//        val stdList = sqlitetaskHelper.getTasksfromList(listId)
        Log.e("pppp", "${stdList.size}")
        sqlitetaskHelper.close()
    }

    private fun clearEditText() {
        var str = ""
        binding.textField.editText?.text = str.toEditable()
        binding.descriptionTextField.editText?.text = str.toEditable()
        binding.textField.requestFocus()
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this.toString())


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}