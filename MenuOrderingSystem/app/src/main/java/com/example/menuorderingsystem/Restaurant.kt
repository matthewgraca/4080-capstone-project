package com.example.menuorderingsystem

class Restaurant {
    val name : String
    val orderManager: OrderManager
    val menuManager: MenuManager

    constructor(name:String, orderManager: OrderManager, menuManager: MenuManager){
        this.name = name;
        this.orderManager = orderManager;
        this.menuManager = menuManager;
    }
}