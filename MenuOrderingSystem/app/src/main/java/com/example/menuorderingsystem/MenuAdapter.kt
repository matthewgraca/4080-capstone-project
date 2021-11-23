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
    lateinit var buttonView: Button

    inner class ViewHolder(menuItem: View): RecyclerView.ViewHolder(menuItem){
//        fun bind(item: Item) {
//            textView.setText(menuItems.get(adapterPosition).getName())
//        }

        val menuItemTextView = menuItem.findViewById<TextView>(R.id.tvItemMenu)
        val buttonTextView = menuItem.findViewById<Button>(R.id.btnAddToOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.menu_style, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = menuItems.get(position)
        textView = holder.menuItemTextView
        when(item){
            is Food -> {
                textView.text = "$${item.getCost()} - ${item.getDescription()}"
            }
            else -> {
                textView.text = "$${item.getCost()}"
            }
        }
        buttonView = holder.buttonTextView
        buttonView.text = item.getName()


//        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }


}