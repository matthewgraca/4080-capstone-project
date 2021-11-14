package com.example.menuorderingsystem

class Drink(name: String, cost: Int, private val size: Size) : Item(name, cost) {
    fun getSize(): Size{
        return size
    }
}

enum class Size {
    SMALL, MEDIUM, LARGE
}
