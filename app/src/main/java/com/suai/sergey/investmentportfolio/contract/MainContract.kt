package com.suai.sergey.investmentportfolio.contract

import com.suai.sergey.investmentportfolio.models.Stock


interface MainContract {

    interface Presenter {

        fun loadStocks()
        fun loadStockPrice(uid: String)
        fun refreshRecyclerView()

    }

    interface View {
        fun updateStockSpinner(stocks: List<Stock>)
        fun updateRecylerViewItem()
        fun refreshRecycerView()
    }
}
