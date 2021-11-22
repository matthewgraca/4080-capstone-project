package com.example.menuorderingsystem

import ItemsAdapter
import ItemsAdapter.*
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var items: ArrayList<Item>
    lateinit var rvItems: RecyclerView
    lateinit var btnFoodItem: Button
    lateinit var btnPlaceOrder: Button
    lateinit var btnClear: Button
    lateinit var btnTable1: Button
    lateinit var btnTable2: Button
    lateinit var btnSaveOrder: Button
    lateinit var  menuItem: ArrayList<Item>
    lateinit var rvMenu: RecyclerView
    lateinit var btnAdd: Button
    lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val pancakes = Item("Pancakes", 4)
        val waffles = Item("Waffles", 4)
        val eggs_ham_bacon = Item("Eggs Ham & Bacon", 4)
        val orderTable1: ArrayList<Item> = arrayListOf(pancakes, waffles, eggs_ham_bacon)
        var o1: Order = Order(orderTable1)
        val table1 = Table(1, o1)

        val mac_cheese = Item("Mac & Cheese", 3)
        val salad = Item("Salad", 3)
        val onion_rings = Item("Onion Rings", 3)
        val fries = Item("Fries", 3)
        val orderTable2: ArrayList<Item> = arrayListOf(mac_cheese, salad, fries, onion_rings)
        var o2: Order = Order(orderTable2)
        val tabl2 = Table(2, o2)

        val i= Item("Nothing in Order Yet", 0)
        val default = arrayListOf(i)
        val defaultOrder= Order(default)
        //items = table.getOrder()      --> more universal
        //and then somehow we add the items to order and update the adapter
        //somehow someway

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instantiate variables
        rvItems = findViewById<View>(R.id.rvItems) as RecyclerView
        rvMenu = findViewById<View>(R.id.rvMenuItems) as RecyclerView
        btnFoodItem = findViewById(R.id.btnFoodItem)
        btnClear = findViewById(R.id.btnClearOrder)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
        btnTable1= findViewById(R.id.btnTable1)
        btnTable2 = findViewById(R.id.btnTable2)
        btnSaveOrder = findViewById(R.id.btnSaveOrder)

        //make method loadItems() in this file that will open up a file within computer to get order
        //make a private method that saves an item into the file and updates it

        items = defaultOrder.getItems()

        itemsAdapter = ItemsAdapter(items)
        itemsAdapter.setOnLongClickListener(object: ItemsAdapter.OnLongClickListener{
            override fun onLongClick(itemView: View, position: Int) {
                items.removeAt(position)
                itemsAdapter.notifyItemRemoved(position)
            }
        })
        rvItems.adapter = itemsAdapter
        rvItems.layoutManager = LinearLayoutManager(this)

        //for menu recycler view
        val mItems: Menu = createMenu()
        menuItem = Item.createItemList(mItems)
        val menuAdapter = MenuAdapter(menuItem)
        rvMenu.adapter = menuAdapter
        rvMenu.layoutManager = LinearLayoutManager(this)

        //when button clicked, add item to menu
        btnFoodItem.setOnClickListener{
            val boba: Item= Item("Boba", 5)
            //get table then get order, then add the item
            items.add(boba)
            itemsAdapter.notifyDataSetChanged()
        }

        btnClear.setOnClickListener{
            items.clear()
            itemsAdapter.notifyDataSetChanged()
        }

        btnPlaceOrder.setOnClickListener{
            //to get us rolling on linking everything idk how to implement this bad boy
            //possibly link a table to the order and call it a day
            Toast.makeText(applicationContext, "Order Placed", Toast.LENGTH_LONG).show()
            items.clear()
            itemsAdapter.notifyDataSetChanged()
        }

        btnTable1.setOnClickListener {
            items.clear()
            items.addAll(table1.getOrder().getItems())
            itemsAdapter.notifyDataSetChanged()
        }

        btnTable2.setOnClickListener {
            items.clear()
            items.addAll(tabl2.getOrder().getItems())
            itemsAdapter.notifyDataSetChanged()
        }

        //hopefully saves the items added to the order
        btnSaveOrder.setOnClickListener {
            Toast.makeText(applicationContext, "Order Saved", Toast.LENGTH_LONG).show()
        }

    }

    fun createMenu():Menu{
        //Breakfast items
        val pancakes = Item("Pancakes", 4)
        val waffles = Item("Waffles", 4)
        val eggs_ham_bacon = Item("Eggs Ham & Bacon", 4)

        //Lunch Items
        val c_burger = Item("Cheeseburger", 5)
        val sandwich = Item("Sandwich", 5)
        val pizza = Item("Pizza", 5)

        //Dinner Items
        val steak = Item("Steak", 16)
        val enchiladas = Item("Enchiladas", 10)
        val tacos = Item("Tacos", 9)
        val lasagna = Item("Lasagna", 10)

        //Sides/Appetizers
        val mac_cheese = Item("Mac & Cheese", 3)
        val salad = Item("Salad", 3)
        val onion_rings = Item("Onion Rings", 3)
        val fries = Item("Fries", 3)


        //Drinks
        val water: Item = Drink("Water", 0, Size.MEDIUM)
        val dr_pepper: Item = Drink("Dr Pepper", 3, Size.MEDIUM)
        val coca_cola: Item = Drink("Coca Cola", 3, Size.MEDIUM)
        var root_beer: Item = Drink("Root Beer", 3, Size.MEDIUM)
        val sprite: Item = Drink("Sprite", 3, Size.MEDIUM)


        val menuWithEverything = arrayListOf(pancakes, waffles, eggs_ham_bacon,
                                c_burger, sandwich, pizza,
                                steak, enchiladas, tacos, lasagna,
                                mac_cheese, salad, onion_rings, fries,
                                water, dr_pepper, coca_cola, root_beer, sprite)
        val motherOfAllMenus :Menu = Menu("Menu with all Item", menuWithEverything)
        return motherOfAllMenus
    }

}
