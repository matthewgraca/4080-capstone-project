package com.example.menuorderingsystem

class Menu(private var name: String,
           private val itemList: Array<Item>) {

    fun getItemList(): Array<Item>{
        return itemList
    }

    fun getName() : String {
        return name
    }

    fun setName(newName : String) :Boolean{
        this.name= newName
        return true
    }

    fun selectItem(item: String): Item? {
        for(i in itemList.indices){
            if(itemList[i].getName() == item)
                return itemList[i]
        }
        return null
    }

    fun printMenu():Boolean{
        if(itemList.isNotEmpty()){
            println("${this.name}:")
            for(i in itemList.indices){
                val itemName: String = itemList[i].getName()
                val itemCost: Int = itemList[i].getCost()
                println("${i+1} $itemName --- $$itemCost")
            }
            return true
        }
        return false
    }

    override fun toString() :String{
        var items :String
        items = "\n$name: \n"
        if(itemList.isNotEmpty()) {
            for (i in itemList.indices) {
                val itemName = itemList[i].getName()
                val itemCost = itemList[i].getCost()

                items = items + ("$itemName: $$itemCost\n")
            }
        }
        return items
    }
}

