package com.suai.sergey.investmentportfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = ArrayList<DataClass>()
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))


        val adapter = DataClassAdapter(list)
        recyclerView.adapter = adapter
    }
}
