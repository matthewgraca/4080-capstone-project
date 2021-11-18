package com.example.menuorderingsystem

import kotlin.Enum as KotlinEnum

open class Item {
    var name: String
    var cost: Int

    constructor(name: String, cost: Int){
        this.name = name;
        this.cost = cost;
    }

    @JvmName("getCost1")
    fun getCost(): Int {
        return cost
    }

    @JvmName("getName1")
    fun getName(): String{
        return name
    }

    override fun toString(): String{
        return "$name: $$cost"
    }

    private fun setCost(nCost:Int): Boolean {
        cost = nCost
        return true;
    }

    private fun setName(nName: String): Boolean {
        name = nName;
        return true
    }

    companion object{
        fun createItemList(menuList: Menu): ArrayList<Item>{
            val itemList = menuList.getItems()
            return itemList
        }
    }

}
