package com.suai.sergey.investmentportfolio.contract

import com.suai.sergey.investmentportfolio.entity.Stock

interface MainContract {

    interface Presenter {

        fun loadStocks()
        fun loadStock()

    }

    interface View {
        fun updateStockSpinner(spinners: List<Stock>)
    }
}