package com.example.menuorderingsystem

import kotlin.collections.*

class MenuManager(private var menuList: Array<Menu>) {

    fun getMenus(): Array<Menu>{
        return menuList
    }

    fun addMenu(menu:Menu):Boolean{
        menuList = menuList.plus(menu)
        return true
    }

    fun selectMenu(selectedMenu: String) : Menu? {
        for(i in menuList.indices){
            if(menuList[i].getName() == selectedMenu){
                return menuList[i]
            }
        }
        return null
    }
    
    fun removeMenu(menu: Menu) :Menu? {
        return if(menuList.contains(menu)) {
            menuList.drop(menuList.indexOf(menu))
            menu
        } else
            null
    }

    override fun toString() :String{
        var menuString = "Menus: "
        for(element in menuList){
            menuString += "$element\n"
        }
        return menuString
    }

}
