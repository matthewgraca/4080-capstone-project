package com.example.menuorderingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        val receipt: Receipt = intent.getSerializableExtra("receipt") as Receipt

        val receiptTextView: TextView = findViewById(R.id.receipt_textview)
        receiptTextView.text = receipt.toString()
    }
}