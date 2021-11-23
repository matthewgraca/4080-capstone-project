package com.example.menuorderingsystem

import java.io.Serializable

class Menu : Serializable {
    
    var name: String
    val itemList: ArrayList<Item>


    constructor(name: String, itemList: ArrayList<Item>){
        this.itemList = itemList
        this.name = name
    }

    fun selectItem(item: String): Item? {
        for(i in 0..itemList.size-1){
            if(itemList[i].getName() == item)
                return itemList[i]
        }
        return null
    }

    fun printMenu():Boolean{
        if(itemList.isNotEmpty()){
            println("${this.name}:")
            for(i in 0..itemList.size-1){
                var itemName: String = itemList[i].getName();
                var itemCost: Int = itemList[i].getCost();
                println("${i+1} $itemName --- $$itemCost")
            }
            return true
        }
        return false
    }

    fun getItems(): ArrayList<Item>{
        return itemList;
    }

    @JvmName("getName1")
    fun getName() : String {
        return name
    }

    fun setName(newName : String) :Boolean{
        this.name= newName;
        return true;
    }

    override fun toString() :String{
        var items :String
        items = "\n$name: \n"
        if(itemList.size > 0) {
            for (i in 0..itemList.size-1) {
                val itemName = itemList[i].getName()
                val itemCost = itemList[i].getCost()

                items = items + ("$itemName: $$itemCost\n")
            }
        }
        return items
    }
}

