package com.example.menuorderingsystem

import java.io.Serializable
import kotlin.Enum as KotlinEnum

open class Item(private var name: String,
                private var cost: Int) : Serializable {

    fun getCost(): Int {
        return cost
    }

    fun getName(): String{
        return name
    }

    fun setCost(cost: Int): Boolean {
        this.cost = cost
        return true
    }

    fun setName(name: String): Boolean {
        this.name = name
        return true
    }

    override fun toString(): String{
        return "$name: $$cost"
    }

    companion object{
        fun createItemList(menuList: Menu): ArrayList<Item>{
            val itemList = menuList.getItems()
            return itemList
        }
    }
}
