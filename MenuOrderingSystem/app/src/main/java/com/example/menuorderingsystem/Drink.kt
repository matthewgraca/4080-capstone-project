package com.example.menuorderingsystem

import java.io.Serializable

class Drink(name: String, cost: Int, private val size: Size) : Item(name, cost), Serializable {
    fun getSize(): Size{
        return size
    }
}

enum class Size {
    SMALL, MEDIUM, LARGE
}
