package com.example.menuorderingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

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

        var tables: ArrayList<Table>? = intent.getSerializableExtra("tables") as ArrayList<Table>?
        if (tables != null) {
            tableList = tables
            println("Tables overrwritten")
        }

        val tableNum = button.text.toString().toInt()
        println("Table #${tableNum}")
        activity2Intent.putExtra("tableNum", tableNum-1)
        activity2Intent.putExtra("tableArray", tableList)
        startActivity(activity2Intent)
    }
}