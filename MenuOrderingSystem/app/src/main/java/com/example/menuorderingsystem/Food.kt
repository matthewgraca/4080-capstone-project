package com.example.menuorderingsystem

class Food : Item {
    val description: String

    constructor(name:String, cost: Int, description: String) : super(name, cost) {
        this.description = description
    }

}