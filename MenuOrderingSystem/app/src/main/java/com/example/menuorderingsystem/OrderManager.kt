package com.example.menuorderingsystem
class OrderManager(private var orderList: MutableList<Order>) {

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