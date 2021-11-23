package com.example.menuorderingsystem

import java.io.Serializable

class Table(private var tableNumber: Int = -1,
            private var order: Order = Order(mutableListOf<Item>())) : Serializable {

    fun getTableNumber(): Int{
        return tableNumber
    }

    fun getOrder(): Order{
        return order
    }

    fun setOrder(o: Order) {
        order = o
    }
}