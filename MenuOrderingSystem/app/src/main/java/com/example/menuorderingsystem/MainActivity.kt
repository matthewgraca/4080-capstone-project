package com.example.menuorderingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var restaurant: Restaurant = createRestaurant()
    var tableList: ArrayList<Table> = arrayListOf(
        Table(1), Table(2), Table(3),
        Table(4), Table(5), Table(6),
        Table(7), Table(8), Table(9),
        Table(10), Table(11), Table(12),
        Table(13), Table(14), Table(15),
        Table(16))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun displayTable(view: View) {
        var activity2Intent = Intent(this, OrderActivity::class.java)

        val button = view as Button

        retrieveData()

        val tableNum = button.text.toString().toInt()
        activity2Intent.putExtra("tableNum", tableNum-1)
        activity2Intent.putExtra("tableArray", tableList)
        activity2Intent.putExtra("restaurant", restaurant)
        startActivity(activity2Intent)
    }

    private fun retrieveData(){
        val tables: ArrayList<Table>? = intent.getSerializableExtra("tables") as ArrayList<Table>?
        val res: Restaurant? = intent.getSerializableExtra("nRestaurant") as Restaurant?
        if (tables != null){
            tableList = tables
        }
        if (res != null){
            restaurant = res
        }
    }

    private fun createRestaurant(): Restaurant{
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

        val breakfast_items = arrayListOf(pancakes, waffles, eggs_ham_bacon) as ArrayList<Item>
        val lunch_items = arrayListOf(c_burger, sandwich, pizza) as ArrayList<Item>
        val dinner_items = arrayListOf(steak, enchiladas, tacos, lasagna) as ArrayList<Item>
        val side_items = arrayListOf(mac_cheese, salad, onion_rings, fries) as ArrayList<Item>
        val drinks = arrayListOf(water, dr_pepper, coca_cola, sprite)

        val breakfastMenu: Menu = Menu("Breakfast", breakfast_items)
        val lunchMenu = Menu("Lunch", lunch_items)
        val dinnerMenu = Menu("Dinner", dinner_items)
        val sideMenu = Menu("Sides & Appetizers", side_items)
        val drinkMenu = Menu("Drinks", drinks)

        val menus = arrayOf(breakfastMenu, lunchMenu, dinnerMenu, sideMenu, drinkMenu)

        val orderManager = OrderManager()
        val menuManager = MenuManager(menus)

        return Restaurant("Kotlin Restaurant",orderManager, menuManager )
    }
}