package com.example.menuorderingsystem

class Order(private var items: MutableList<Item>) {
    private var orderNumber = 0

    fun getOrderNumber(): Int{
        return orderNumber
    }

    fun setOrderNumber(num: Int){
        orderNumber = num
    }

    fun addItem(item: Item): Boolean {
        items.add(item)
        return true
    }

    fun removeItem(item: Item): Boolean {
        items.remove(item)
        return true
    }

    fun getItems(): MutableList<Item>{
        return items
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun generateReceipt(amount: Int): Receipt{
        return Receipt(PayMethod.CASH, calculateTotal(), amount, this.toString())
    }

    private fun calculateTotal(): Int {
        var orderTotal = 0
        for (item in items) {
            orderTotal += item.getCost()
        }
        return orderTotal
    }

    override fun toString(): String {
        var orderOutput = "Order: "
        orderOutput = orderOutput.plus(orderNumber).plus("\n")
        val iterator = items.listIterator()
        for (item in iterator) {
            orderOutput = orderOutput.plus(item.getName()).plus(": $").plus(item.getCost()).plus("\n")
        }
        return orderOutput
    }
}
