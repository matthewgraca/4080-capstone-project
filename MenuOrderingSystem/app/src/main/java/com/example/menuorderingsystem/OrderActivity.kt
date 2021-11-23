package com.example.menuorderingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderActivity : AppCompatActivity() {

    lateinit var items: MutableList<Item>
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)



        rvMenu = findViewById<View>(R.id.rvMenuItems) as RecyclerView



        rvItems.layoutManager = LinearLayoutManager(this)

        //for menu recycler view
        val mItems: Menu = createMenu()
        menuItem = Item.createItemList(mItems)
        val menuAdapter = MenuAdapter(menuItem)
        rvMenu.adapter = menuAdapter
        rvMenu.layoutManager = LinearLayoutManager(this)


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
