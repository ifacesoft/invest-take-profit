package com.suai.sergey.investmentportfolio.presenters

import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.interactors.StockInteractor
import java.util.*

class MainPresenter(view: MainContract.View, private var stockInteractor: StockInteractor) : MainContract.Presenter, Observer {
    override fun update(o: Observable?, arg: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadStockPrice() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadStocks() {
        stockInteractor.loadData()
    }
}