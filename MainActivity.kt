package com.example.menuorderingsystem

import ItemsAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        val i1 = Item("Boba", 5)
        val i2 = Item("Tea", 3)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById<View>(R.id.rvItems) as RecyclerView
        val m: Menu = createMenu();
        items = Item.createItemList(m)
        items.add(i1)
        items.add(i2)
        val adapter = ItemsAdapter(items)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)

        //when button clicked, add item to menu
        btnFoodItem = findViewById(R.id.btnFoodItem);
        btnFoodItem.setOnClickListener{
            val boba: Item= Item("Boba", 5)
            items.add(boba)
            adapter.notifyDataSetChanged();
        }

        btnClear = findViewById(R.id.btnClearOrder);
        btnClear.setOnClickListener{v->
            items.clear()
            adapter.notifyDataSetChanged()
        }

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
        btnPlaceOrder.setOnClickListener{
            //to get us rolling on linking everything idk how to implement this bad boy
            //possibly link a table to the order and call it a day
            val order: Order = Order(items)
            Toast.makeText(applicationContext, "Order Placed", Toast.LENGTH_LONG).show()
            items.clear()
            adapter.notifyDataSetChanged()
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
