package com.suai.sergey.investmentportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.entity.Stock
import com.suai.sergey.investmentportfolio.interactors.StockInteractor
import com.suai.sergey.investmentportfolio.presenters.MainPresenter
import android.content.Intent
import com.suai.sergey.investmentportfolio.services.UpdateCurrentPrices
import android.os.Build




class MainActivity : AppCompatActivity(), MainContract.View {

    private var mainPresenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainPresenter = MainPresenter(this, StockInteractor())
        (mainPresenter as MainPresenter).loadStocks()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, UpdateCurrentPrices::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    override fun updateStockSpinner(spinners: List<Stock>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
