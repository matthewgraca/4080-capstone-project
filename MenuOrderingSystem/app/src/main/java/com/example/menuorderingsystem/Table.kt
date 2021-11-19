package com.example.menuorderingsystem

class Table(private var tableNumber: Int,
            private var order: Order) {

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