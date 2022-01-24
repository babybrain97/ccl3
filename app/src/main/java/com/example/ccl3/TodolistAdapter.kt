package com.example.ccl3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.TodolistItemViewBinding


class TodolistAdapter(val context: HomeFragment, val items: List<TodolistsModelDB>) : RecyclerView.Adapter<TodolistAdapter.ViewHolder>() {
//class TodolistAdapter: RecyclerView.Adapter<TodolistAdapter.ViewHolder>() {

//    private var listsList: Array<TodolistItem> = ArrayList()

//    fun addItems(items:Array<TodolistItem>) {
//        this.listsList = items
//    }
//    val tasks= arrayOf("Today's Tasks", "Weekly To Do", "App Design")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodolistItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val item = items.get(position)
    holder.binding.toDoListItemText.text = item.name

//    holder.binding.toDoListItemText.text = listsList[position].toString()
//    holder.binding.toDoListItemText.text = tasks [position]
//            with(holder){
//                with(listsList[position]) {
//                    binding.toDoListItemText
//                }
//            }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return items.size
//        return listsList.size
//        return tasks.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(val binding: TodolistItemViewBinding) :RecyclerView.ViewHolder(binding.root)
}

