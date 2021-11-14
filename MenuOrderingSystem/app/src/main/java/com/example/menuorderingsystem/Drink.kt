package com.example.menuorderingsystem

class Drink : Item{
    val size: Size

    constructor(name: String, cost: Int, size: Size): super(name, cost){
        this.size = size
    }
}

enum class Size {
    SMALL, MEDIUM, LARGE
}
