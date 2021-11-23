package com.example.menuorderingsystem

import java.io.Serializable

class Food(name: String,
           cost: Int,
           private val description: String) : Item(name, cost), Serializable {

    fun getDescription(): String{
        return description
    }
}