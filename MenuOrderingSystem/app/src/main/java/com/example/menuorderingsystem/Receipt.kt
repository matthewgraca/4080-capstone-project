package com.example.menuorderingsystem

enum class PayMethod {
    CASH,
    DEBIT,
    CREDIT
}

class Receipt(private var payMethod: PayMethod,
              private var total: Int,
              amount: Int,
              private var items: String) {

    private var change = amount - total

    override fun toString(): String {
        return "Receipt:\n" + items + "\nPayment Method: " + payMethod + "\nCash: $"+ (change + total) + "\n" + "Change: $" + change + "\nTotal: $" + total
    }
}
