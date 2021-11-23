package com.example.menuorderingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView

class OrderActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var currentTable: Table
    lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

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

        val burger: Item = Item("Burger", 2)
        val sandwich: Item = Item("Sandwich", 2)
        val hotdog: Item = Item("Hotdog", 2)
        val items: MutableList<Item> = mutableListOf(hotdog, burger, sandwich)
        for ( i in 0..15){
            items.add(hotdog)
        }
        var o: Order = Order(items)
        var table1: Table = Table(1, o)
        currentTable = table1

        var x = 0
        for (i in currentTable.getOrder().getItems()){
            //var rand = Random(1000000)
            var item: MenuItem = navView.menu.add(R.id.order_list, x, 0, i.getName())
            item.isCheckable = true
            x++
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.groupId == R.id.order_list){
            navView.menu.removeItem(item.itemId)
            return true
        }
        return super.onContextItemSelected(item)
    }
}