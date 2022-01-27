package com.example.ccl3

import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.TaskItemViewBinding


class TaskAdapter(val items: ArrayList<TasksModelDB>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    fun deleteItem(index: Int){
        items.removeAt(index)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {

        val item = items.get(position)
        holder.setItem(item)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: TaskItemViewBinding) :RecyclerView.ViewHolder(binding.root){
        private lateinit var item : TasksModelDB

        fun setItem(item : TasksModelDB){
            this.item = item
            binding.taskText.text = item.nameTask
            binding.taskDescriptionText.text = item.description
        }

        fun getID(): Int {
            return item.id
        }
    }

}