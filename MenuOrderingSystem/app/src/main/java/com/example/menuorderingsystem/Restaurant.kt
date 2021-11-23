package com.example.menuorderingsystem

import java.io.Serializable

class Restaurant(private val name: String,
                 private val orderManager: OrderManager,
                 private val menuManager: MenuManager) : Serializable {

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