package com.example.menuorderingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class OrderActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var currentTable: Table
    lateinit var tables: ArrayList<Table>
    var tableNum: Int = 0
    lateinit var navView: NavigationView
    lateinit var  menuItem: ArrayList<Item>
    lateinit var rvMenu: RecyclerView
    lateinit var mItems: Menu
    lateinit var rIntent: Intent
    lateinit var orderButton: Button
    lateinit var restaurant: Restaurant
    private var newItems: ArrayList<Item> = ArrayList<Item>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        rIntent = Intent(this, MainActivity::class.java)

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.getHeaderView(R.id.textView)

        restaurant = intent.getSerializableExtra("restaurant") as Restaurant
        tables = intent.getSerializableExtra("tableArray") as ArrayList<Table>
        tableNum = intent.getSerializableExtra("tableNum") as Int
        currentTable = tables[tableNum]
        displayCurrentOrder()

        val textView: TextView = navView.getHeaderView(0).findViewById(R.id.textView) as TextView
        textView!!.text = "Table #${tableNum+1}"

        orderButton = findViewById(R.id.order_button)
        if(!currentTable.getOrder().isEmpty())
            orderButton.text = getString(R.string.update)


        val receiptButton: Button = findViewById(R.id.receipt_button)
        val paidButton: Button = findViewById(R.id.paid_button)

        // paid button functionality
        paidButton.setOnClickListener{
            // show paid msg
            val toast = Toast.makeText(this, "Order Paid!", Toast.LENGTH_SHORT)
            toast.show()
            currentTable.setOrder(Order())
            tables[tableNum] = currentTable
            navView.menu.clear()
            //finish()
        }

        // order button functionality
        orderButton.setOnClickListener{

            if(orderButton.text == getString(R.string.update) ){
                updateOrder()
            }
            else{
                for(i in newItems){
                    currentTable.getOrder().addItem(i)
                }
                newItems.clear()
                restaurant.getOrderManager().addOrder(currentTable.getOrder())
            }

            // display order sent msg
            val toast: Toast = Toast.makeText(this, "Order Sent!", Toast.LENGTH_SHORT)
            toast.show()

            // disable button when order is sent
            orderButton.isEnabled = false
            orderButton.text = getString(R.string.update)
        }

        // receipt button functionality
        receiptButton.setOnClickListener{
            // move to receipt activity to handle the receipt
            val receipt: Receipt = currentTable.getOrder().generateReceipt(300)

            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("receipt", receipt)
            startActivity(intent)
        }


        rvMenu = findViewById<View>(R.id.rvMenuItems) as RecyclerView

        //for menu recycler view
        mItems = createMenu()
        menuItem = Item.createItemList(mItems)
        val menuAdapter = MenuAdapter(menuItem, false)
        rvMenu.adapter = menuAdapter
        rvMenu.layoutManager = LinearLayoutManager(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        rvMenu.layoutManager = GridLayoutManager(this, 4)
        if(item.groupId == R.id.order_list){
            navView.menu.removeItem(item.itemId)
            return true
        }
        return super.onContextItemSelected(item)
    }


    private fun displayCurrentOrder(){
        val order = currentTable.getOrder().getItems()

        for((counter, i) in order.withIndex()){
            navView.menu.add(R.id.order_list, counter, 0, i.getName())
        }
    }

    fun orderAddItem(view: View){
        val button: Button = view as Button

        val selected = button.text
        val item = mItems.selectItem(selected as String)

        newItems.add(item!!)

        val itemView: MenuItem = navView.menu.add(R.id.order_list, navView.menu.size(), 0, item.getName())
        itemView.isCheckable = true

        orderButton.isEnabled = true
    }

    private fun updateOrder(){
        if(newItems.isEmpty())
            return

        val orders = restaurant.getOrderManager().getOrder()
        val oldOrder = currentTable.getOrder()
        for(i in orders){
            println(i)
            if(i.getOrderNumber() == oldOrder.getOrderNumber()){
                for(j in newItems){
                    i.addItem(j)
                    currentTable.getOrder().addItem(j)
                }
                break
            }
        }

        newItems.clear()
        tables[tableNum] = currentTable
    }

    override fun onBackPressed() {
        updateOrder()
        rIntent.putExtra("tables", tables)
        rIntent.putExtra("nRestaurant", restaurant)
        startActivity(rIntent)
    }

    fun layoutSwitch(view: View){
        val button = view as Button

        when(button.text){
            "Linear" -> {
                rvMenu.adapter = MenuAdapter(menuItem, false)
                rvMenu.layoutManager = LinearLayoutManager(this)
                button.text = "Grid"
            }
            "Grid" -> {
                rvMenu.adapter = MenuAdapter(menuItem, true)
                rvMenu.layoutManager = GridLayoutManager(this, 4)
                button.text = "Linear"
            }
        }
    }


    private fun createMenu(): Menu {

        val menus = restaurant.getMenuManager().getMenus()
        var menuWithEverything = ArrayList<Item>()
        for (i in menus) {
            for (j in i.itemList) {
                menuWithEverything.add(j)
            }
        }
        return Menu("Menu with all Item", menuWithEverything)
    }
}