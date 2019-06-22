package com.suai.sergey.investmentportfolio.presenters

import android.os.Looper
import android.util.Log
import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.interactors.BuyInteractor
import com.suai.sergey.investmentportfolio.interactors.RefreshingInteractor
import com.suai.sergey.investmentportfolio.interactors.StockInteractor
import com.suai.sergey.investmentportfolio.interactors.StockPriceInteractor
import com.suai.sergey.investmentportfolio.models.Stock
import java.util.*

class MainPresenter(
    private var view: MainContract.View,
    private var stockInteractor: StockInteractor,
    private var stockPriceInteractor: StockPriceInteractor,
    private var refreshingInteractor: RefreshingInteractor,
    private var buyInteractor: BuyInteractor
) :
    MainContract.Presenter, Observer {
    override fun sellDeal(uid: String) {
        buyInteractor.addObserver(this)
        buyInteractor.sellDeal(uid)
    }

    override fun buyDeal(uid: String) {
        buyInteractor.addObserver(this)
        buyInteractor.buyDeal(uid)
    }

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

            obeservable is StockPriceInteractor -> {
                @Suppress("UNCHECKED_CAST")
                view.updateRecylerViewItem()
            }

            obeservable is RefreshingInteractor -> {
                @Suppress("UNCHECKED_CAST")
                view.refreshRecycerView((parcel as List<Stock>))
            }
        }
    }

    override fun loadStockPrice(uid: String) {
        stockPriceInteractor.addObserver(this)
        stockPriceInteractor.loadData(uid)
    }

    override fun loadStocks() {
        stockInteractor.addObserver(this)
        stockInteractor.loadData()
    }

    override fun refreshRecyclerView() {
        Log.d("myLogs", "refreshRecyclerView")
        refreshingInteractor.addObserver(this)
        refreshingInteractor.loadData()
    }
}