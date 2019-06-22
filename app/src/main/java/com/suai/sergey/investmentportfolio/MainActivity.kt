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
import android.content.Intent
import com.suai.sergey.investmentportfolio.services.UpdateCurrentPrices
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.ItemTouchHelper
import com.suai.sergey.investmentportfolio.fragments.BuyDialogFragment
import com.suai.sergey.investmentportfolio.fragments.SellDialogFragment
import com.suai.sergey.investmentportfolio.interactors.BuyInteractor
import com.suai.sergey.investmentportfolio.interactors.RefreshingInteractor
import com.suai.sergey.investmentportfolio.interactors.StockPriceInteractor
import com.suai.sergey.investmentportfolio.recycler_view.DataClassAdapter

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var shortsName: String
    lateinit var longsName: String
    lateinit var costs: String

    private var recyclerView: RecyclerView? = null

    private val spinnerAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList<String>())
    }

    var recyclerViewData: ArrayList<Stock> = ArrayList<Stock>()

    private val recyclerViewAdapter: DataClassAdapter by lazy {
        DataClassAdapter(recyclerViewData)
    }

    var spinnerData: List<Stock> = emptyList()
    var mainPresenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(
            this,
            StockInteractor(),
            StockPriceInteractor(),
            RefreshingInteractor(),
            BuyInteractor()
        )
        (mainPresenter as MainPresenter).loadStocks()
        makeRecycleView()
        makeSpinner()
        swipeListener()

        InvestTakeProfitApplication.observableStocks.listener = {
            updateRecylerViewItem()
        }

        startForegroundService(Intent(this, UpdateCurrentPrices::class.java))

        (mainPresenter as MainPresenter).buyDeal("SBER")
        (mainPresenter as MainPresenter).sellDeal("SBER")
    }

    private fun makeRecycleView() {
        val tvRecyclerView: TextView = findViewById(R.id.tv_recyclerView)
        recyclerView = findViewById(R.id.recycler_view)
//        if (dataList().size > 0) {
        tvRecyclerView.visibility = View.INVISIBLE
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = recyclerViewAdapter
//        } else {
////            tvRecyclerView.visibility = View.VISIBLE
//        }
    }

    private fun makeSpinner() {
        val spinner: Spinner = findViewById(R.id.spinner_id)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        //spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        (spinnerAdapter as ArrayAdapter<*>).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = spinnerData.get(position)
                mainPresenter?.loadStockPrice(item.getStock_uid())
                toast(item.getStock_uid())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun swipeListener() {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    var bought: Int = 1

                    if (direction == ItemTouchHelper.LEFT) {
                        if (bought == 1) {
                            SellDialogFragment().apply {
                                arguments = Bundle().apply {
                                    putString(
                                        "key",
                                        spinnerData.get(viewHolder.adapterPosition).getStock_uid()
                                    )
                                }
                            }.show(supportFragmentManager, "sell")
                            recyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                        } else {
                            recyclerViewAdapter.removeItem(viewHolder)
                        }
                    } else if (direction == ItemTouchHelper.RIGHT) {
                        if (bought == 1) {
                            recyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                        } else {
                            BuyDialogFragment().apply {
                                arguments = Bundle().apply {
                                    putString(
                                        "key",
                                        spinnerData.get(viewHolder.adapterPosition).getStock_uid()
                                    )
                                }
                            }.show(supportFragmentManager, "sell")
                            recyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                        }
                    }
                } else if (direction == ItemTouchHelper.RIGHT) {
                    BuyDialogFragment().show(supportFragmentManager, "buy")
                    recyclerViewAdapter.notifyItemChanged(viewHolder.adapterPosition)
                }


            }

        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun updateStockSpinner(stocks: List<Stock>) {
        spinnerData = stocks

        spinnerAdapter.clear()
        spinnerAdapter.addAll(stocks.map { it.getStock_name() })
        spinnerAdapter.notifyDataSetChanged()
    }

    override fun updateRecylerViewItem() {
        mainPresenter!!.refreshRecyclerView()
//        val myList: ArrayList<Stock> = ArrayList<Stock>()
//        myList.add(Stock(0, "ARLS", "ALROSA"))
//        recyclerViewData.addAll(myList)
//        recyclerViewAdapter.notifyDataSetChanged()

        // TODO("OMFG!!!") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshRecycerView(stocks: List<Stock>) {
        if (!stocks.isEmpty()) {
            Handler(Looper.getMainLooper()).post {
                recyclerViewData.clear()
                recyclerViewData.addAll(stocks)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

}
