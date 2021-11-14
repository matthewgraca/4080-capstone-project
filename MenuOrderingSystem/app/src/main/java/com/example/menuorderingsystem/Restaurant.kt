package com.example.menuorderingsystem

class Restaurant(private val name: String,
                 private val orderManager: OrderManager,
                 private val menuManager: MenuManager) {

    fun getName(): String{
        return name
    }

    fun getOrderManager(): OrderManager{
        return orderManager
    }

    fun getMenuManager(): MenuManager{
        return menuManager
    }
}