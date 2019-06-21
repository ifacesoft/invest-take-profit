package com.suai.sergey.investmentportfolio.contract

import com.suai.sergey.investmentportfolio.models.Stock


interface MainContract {

    interface Presenter {

        fun loadStocks()
        fun loadStockPrice()

    }

    interface View {
        fun updateStockSpinner(spinner: List<Stock>)
    }
}
