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

    lateinit var shortsName: String
    lateinit var longsName: String
    lateinit var costs: String

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun updateStockSpinner(spinner: List<Stock>) {
        spinner.get(1)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private var mainPresenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {




        mainPresenter = MainPresenter(this, StockInteractor());
        (mainPresenter as MainPresenter).loadStocks()



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeRecycleView()
        makeSpinner()
    }

    private fun dataList(): ArrayList<DataClass> {
        val list = ArrayList<DataClass>()
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))
//        list.add(DataClass("USDD", "ГАхзпромсясцо", "10000$$"))

        return list
    }

    private fun spinnerList(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        arrayList.add(" ")
        arrayList.add("ГАхзпромсясцо")
        arrayList.add("ГАхзпромсясцо")
        arrayList.add("ГАхзпромсясцо")
        arrayList.add("ГАхзпромсясцо")
        arrayList.add("ГАхзпромсясцо")
        arrayList.add("ГАхзпромсясцо")
        return arrayList
    }

    private fun makeRecycleView() {
        val tvRecyclerView: TextView = findViewById(R.id.tv_recyclerView)

        if (dataList().size > 0) {
            tvRecyclerView.visibility = View.INVISIBLE
            val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = DataClassAdapter(dataList())
            recyclerView.adapter = adapter
        } else {
            tvRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun makeSpinner() {
        val spinner: Spinner = findViewById(R.id.spinner_id)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList().toArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) {
                    val item = parent.getItemAtPosition(position) as String
                    toast(item)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
