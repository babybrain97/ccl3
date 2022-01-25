package com.example.ccl3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.TaskItemViewBinding


class TaskAdapter(val items: List<TasksModelDB>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {

        val item = items.get(position)
        holder.binding.taskText.text = item.nameTask
        holder.binding.taskDescriptionText.text = item.description

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: TaskItemViewBinding) :RecyclerView.ViewHolder(binding.root)


}