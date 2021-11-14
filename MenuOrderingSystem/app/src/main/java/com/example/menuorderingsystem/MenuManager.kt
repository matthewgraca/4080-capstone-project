package com.example.menuorderingsystem

import kotlin.collections.indexOf as indexOf1

class MenuManager {
    var menuList: Array<Menu>

    constructor(menuList:Array<Menu>){
        this.menuList = menuList
    }

    fun getMenus(): Array<Menu>{
        return menuList;
    }

    fun addMenu(menu:Menu):Boolean{
        menuList = menuList.plus(menu)
        return true
    }

    fun selectMenu(selectedMenu: String) : Menu? {
        for(i in 0..menuList.size-1){
            if(menuList[i].getName().equals(selectedMenu)){
                return menuList[i]
            }
        }
        return null
    }
    
    fun removeMenu(menu: Menu) :Menu? {
        if(menuList.contains(menu)){
            var tempMenu = menu
            menuList.drop(menuList.indexOf1(menu))
            return tempMenu;
        }
        else
            return null
    }

    override fun toString() :String{
        var sMenu = "Menus: "
        for(i in 0..menuList.size-1){
            var menuName = menuList[i]
            sMenu = sMenu + "$menuName\n"
        }
        return sMenu
    }

}
