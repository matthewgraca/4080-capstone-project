package com.example.menuorderingsystem

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        rIntent = Intent(this, MainActivity::class.java)

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.getHeaderView(R.id.textView)

        tables = intent.getSerializableExtra("tableArray") as ArrayList<Table>
        tableNum = intent.getSerializableExtra("tableNum") as Int
        currentTable = tables[tableNum]
        displayCurrentOrder()

        val textView: TextView = navView.getHeaderView(0).findViewById(R.id.textView) as TextView
        textView!!.text = "Table #${tableNum+1}"

        orderButton = findViewById(R.id.order_button)


        val receiptButton: Button = findViewById(R.id.receipt_button)
        val paidButton: Button = findViewById(R.id.paid_button)

        // paid button functionality
        paidButton.setOnClickListener{
            // show paid msg
            val toast = Toast.makeText(this, "Order Paid!", Toast.LENGTH_SHORT)
            toast.show()
            currentTable.setOrder(Order())
            tables[tableNum] = currentTable
            //finish()
        }

        // order button functionality
        orderButton.setOnClickListener{

            tables[tableNum] = currentTable

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
            val receipt: Receipt = currentTable.getOrder().generateReceipt(100)

            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("receipt", receipt)
            startActivity(intent)
        }


        rvMenu = findViewById<View>(R.id.rvMenuItems) as RecyclerView

        //for menu recycler view
        mItems = createMenu()
        menuItem = Item.createItemList(mItems)
        val menuAdapter = MenuAdapter(menuItem)
        rvMenu.adapter = menuAdapter
        rvMenu.layoutManager = LinearLayoutManager(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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
        var item = mItems.selectItem(selected as String)


        var order = currentTable.getOrder()

        order.addItem(item!!)


        var itemView: MenuItem = navView.menu.add(R.id.order_list, order.getItems().size, 0, item.getName())
        itemView.isCheckable = true

        orderButton.isEnabled = true
    }

    override fun onBackPressed() {
        rIntent.putExtra("tables", tables)
        startActivity(rIntent)
    }


    fun createMenu():Menu{
        //Breakfast items
        val pancakes = Food("Pancakes", 4, "3 pancakes stacked on top of each other with a side of butter and a choice of toppings strawberries/ chocolate chips / cherries")
        val waffles = Food("Waffles", 4, "1 waffle topped with whipped cream and a choice of toppings strawberry/choccolate chips / cherries")
        val eggs_ham_bacon = Food("Eggs Ham & Bacon", 4, " A plate filled with eggs (choice of scrambled or sunny side-up), bacon, and ham with a side of roasted potatoes or hash browns")

        //Lunch Foods
        val c_burger = Food("Cheeseburger", 10, "A juicy all-beef burger classic with american cheese, lettuce, tomato, onion, and pickles on a Brioche bun.")
        val sandwich = Food("Sandwich", 7, "Turkey, ham, american cheese, lettuce, tomato, and mayonnaise on wheat bread.")
        val pizza = Food("Pizza", 9, "Garlic oil sauce, ricotta & Mozzarella cheese, tomatoes and basil")

        //Dinner Items
        val steak = Food("Steak", 16, "Pan-seared steak with garlic butter is seared and caramelized on the outside and juicy inside")
        val enchiladas = Food("Enchiladas", 10, "Flour tortilla rolled around choice of chicken or beef with a savory sauce")
        val tacos = Food("Tacos", 9, "Six Corn tortillas topped with choice of chicken, beef, or fish")
        val lasagna = Food("Lasagna", 10, "With basil, sausage, ground beef and three types of cheese")

        //Sides/Appetizers
        val mac_cheese = Food("Mac & Cheese", 3, "Macaroni, butter, milk with cheddar and parmesan cheese")
        val salad = Food("Salad", 3, "Lettuce, tomato, cucumber, carrot served with ranch dressing")
        val onion_rings = Food("Onion Rings", 3, "Deep fried onions")
        val fries = Food("Fries", 3, "Crispy fries, light sea salt")


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
        val motherOfAllMenus: Menu = Menu("Menu with all Item", menuWithEverything)
        return motherOfAllMenus
    }
}