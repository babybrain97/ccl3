package com.example.ccl3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.TodolistItemViewBinding


class TodolistAdapter(
    private val items: List<TodolistsModelDB>,
    private val onItemClicked: (TodolistsModelDB) -> Unit
) :
    RecyclerView.Adapter<TodolistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = TodolistItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.toDoListItemText.text = item.name
        holder.binding.todolistItem.setOnClickListener {
            onItemClicked(items[position])
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: TodolistItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
