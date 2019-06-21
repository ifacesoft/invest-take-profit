package com.suai.sergey.investmentportfolio.presenters

import android.os.Looper
import android.util.Log
import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.interactors.StockInteractor
import com.suai.sergey.investmentportfolio.models.Stock
import java.util.*

class MainPresenter(private var view: MainContract.View, private var stockInteractor: StockInteractor) :
    MainContract.Presenter, Observer {
    private val TAG = "Invest_MainContract"


    override fun update(obeservable: Observable?, parcel: Any?) {

        Log.d(TAG, "Updated")

        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d(TAG, "MAIN")
        } else {
            Log.d(TAG, "NOT_MAIN")
        }

        when {
            obeservable is StockInteractor -> {
                @Suppress("UNCHECKED_CAST")
                view.updateStockSpinner((parcel as List<Stock>))
            }

        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadStockPrice() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadStocks() {
        stockInteractor.addObserver(this)
        stockInteractor.loadData()
    }
}