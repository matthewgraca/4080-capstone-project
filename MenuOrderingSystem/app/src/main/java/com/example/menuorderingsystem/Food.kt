package com.example.menuorderingsystem

class Food(name: String,
           cost: Int,
           private val description: String) : Item(name, cost) {

    fun getDescription(): String{
        return description
    }
}