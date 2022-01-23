package com.example.ccl3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccl3.databinding.TodolistItemViewBinding

//class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
//    private var stdList: ArrayList<ListModelDb> = ArrayList()
//
//    fun addItems(items: ArrayList<ListModelDb>){
//        this.stdList = items
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ListViewHolder (
//        LayoutInflater.from(parent.context).inflate(R.layout.todolist_item_view, parent, false)
//    )
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val std = stdList[position]
////        holder.binding.toDoListItemText.text = tasks [position]
//        holder.bindView(std)
//    }
//
//    override fun getItemCount(): Int {
//        return stdList.size
//    }
//
//   // inner class ViewHolder(val binding: TodolistItemViewBinding) : RecyclerView.ViewHolder(binding.root)
//
//    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        private var id = view.findViewById<TextView>(R.id.)
//    }
//
//}
