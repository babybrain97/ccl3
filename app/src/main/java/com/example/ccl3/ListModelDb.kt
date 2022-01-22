package com.example.ccl3

import java.util.*

data class ListModelDb(
    var id: Int = getAutoId(),
    var name: String = "",
   // var dateFrom: String = "",
    //var dateTo: String = "",
    var reward: String = ""
){
    companion object{
    fun getAutoId():Int {
        val random = Random()
        return random.nextInt(100)
        }
    }

}

