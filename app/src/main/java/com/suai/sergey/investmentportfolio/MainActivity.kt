package com.suai.sergey.investmentportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.suai.sergey.investmentportfolio.contract.MainContract
import com.suai.sergey.investmentportfolio.entity.Stock

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun updateStockSpinner(spinners: List<Stock>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
