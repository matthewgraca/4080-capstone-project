package com.example.menuorderingsystem

import java.io.Serializable

class OrderManager(private var orderList: MutableList<Order> = mutableListOf()) : Serializable {

    fun getOrder(): MutableList<Order>{
        return orderList
    }

    fun addOrder(order: Order):Boolean {
        order.setOrderNumber(orderList.size + 1)
        orderList.add(order)
        return true
    }
    fun removeOrder(order: Order): Boolean {
        orderList.remove(order)
        return true
    }

}