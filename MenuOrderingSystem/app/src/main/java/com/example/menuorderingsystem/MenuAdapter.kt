package com.example.menuorderingsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter (private val menuItems: List<Item>): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    interface OnClickListener{
        fun onItemClicked(pos: Int)
    }

    lateinit var textView: TextView

    inner class ViewHolder(menuItem: View): RecyclerView.ViewHolder(menuItem){
//        fun bind(item: Item) {
//            textView.setText(menuItems.get(adapterPosition).getName())
//        }

        val menuItemTextView = menuItem.findViewById<TextView>(R.id.tvItemMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.menu_style, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = menuItems.get(position)
        textView = holder.menuItemTextView
        textView.setText(item.name)
//        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }


}