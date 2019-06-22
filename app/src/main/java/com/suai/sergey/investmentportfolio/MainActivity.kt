package com.suai.sergey.investmentportfolio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.interactors.StockInteractor
import com.suai.sergey.investmentportfolio.models.Stock
import com.suai.sergey.investmentportfolio.presenters.MainPresenter
import com.suai.sergey.investmentportfolio.recycler_view.DataClass
import com.suai.sergey.investmentportfolio.recycler_view.DataClassAdapter


class MainActivity : AppCompatActivity(), MainContract.View {

    private var recyclerView: RecyclerView? = null
    private var dataList: List<DataClass> = emptyList()
    private val spinnerAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<String>())
    }

    private var recyclerViewAdapter: DataClassAdapter? = null

    private var spinnerData: List<Stock> = emptyList()
    private var mainPresenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, StockInteractor())
        (mainPresenter as MainPresenter).loadStocks()

        makeRecycleView()
        makeSpinner()
    }

    private fun makeRecycleView() {
        val tvRecyclerView: TextView = findViewById(R.id.tv_recyclerView)
        recyclerView = findViewById(R.id.recycler_view)
        if (dataList.isNotEmpty()) {
            tvRecyclerView.visibility = View.INVISIBLE
            recyclerView!!.layoutManager = LinearLayoutManager(this)
            recyclerViewAdapter = DataClassAdapter(dataList)
            recyclerView!!.adapter = recyclerViewAdapter
        } else {
            tvRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun makeSpinner() {
        val spinner: Spinner = findViewById(R.id.spinner_id)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        //spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        (spinnerAdapter as ArrayAdapter<*>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) {
                    val item = spinnerData[position]
                    toast(item.getStock_uid())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun updateStockSpinner(spinner: List<Stock>) {

        spinnerData = spinner

        spinnerAdapter.clear()
        spinnerAdapter.addAll(spinner.map { it.getStock_name() })
        spinnerAdapter.notifyDataSetChanged()

    }

    fun updateRecylerViewData(stock: Stock) {

        recyclerViewAdapter!!.notifyDataSetChanged()
    }

}


