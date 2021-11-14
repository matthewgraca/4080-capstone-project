package com.example.menuorderingsystem

enum class PayMethod {
    CASH,
    DEBIT,
    CREDIT
}

class Receipt {
    var payMethod: PayMethod
    var change: Int
    var items: String
    var total: Int

    constructor(payMethod: PayMethod, total: Int, amount: Int, bill: String) {
        this.payMethod = payMethod
        this.change = amount - total
        this.total = total
        this.items = bill
    }

    override fun toString(): String {
        var orderOutput = "Receipt:\n" + items + "\nPayment Method: " + payMethod + "\nCash: $"+ (change + total) + "\n" + "Change: $" + change + "\nTotal: $" + total
        return orderOutput
    }
}
