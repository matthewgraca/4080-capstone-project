package com.example.menuorderingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val orderButton: Button = findViewById(R.id.order_button)
        val receiptButton: Button = findViewById(R.id.receipt_button)
        val paidButton: Button = findViewById(R.id.paid_button)

        // paid button functionality
        paidButton.setOnClickListener{
            // show paid msg
            val toast = Toast.makeText(this, "Order Paid!", Toast.LENGTH_SHORT)
            toast.show()
            // TODO:clear order (something like thisOrder.clear()?)
            // return to tables (returns to caller; main in this case, should be tableactivity)
            finish()
        }

        // order button functionality
        orderButton.setOnClickListener{
            // TODO:place order (we don't have a kitchen that accepts the order, so where does this go?)
            // display order sent msg
            val toast = Toast.makeText(this, "Order Sent!", Toast.LENGTH_SHORT)
            toast.show()
            // disable button when order is sent
            orderButton.isEnabled = false
            // TODO:detect change in order (that's handled outside this block when list is updated)
            /* on their end: like if orderButton.text == update, then orderButton.isEnabled == true*/
            // change button to "update" when ordered
            orderButton.text = getString(R.string.update)
        }

        // receipt button functionality
        receiptButton.setOnClickListener{
            // move to receipt activity to handle the receipt
            val intent = Intent(this, ReceiptActivity::class.java)
            startActivity(intent)
        }
    }
}