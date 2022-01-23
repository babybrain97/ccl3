package com.example.ccl3

import java.util.*

//data class TodolistItem(val text: String)
class TodolistItem(
    val id: Int = getAutoId(),
    val name: String,
    // var dateFrom: String = "",
    //var dateTo: String = "",
    val reward: String
){
    companion object{
        fun getAutoId():Int {
            val random = Random()
            return random.nextInt(100)
        }
    }

}