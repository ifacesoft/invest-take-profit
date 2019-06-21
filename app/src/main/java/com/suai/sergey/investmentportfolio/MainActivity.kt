package com.suai.sergey.investmentportfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suai.sergey.investmentportfolio.contract.MainContract

class MainActivity :  AppCompatActivity() , MainContract.View {

    override fun updateStockSpinner(spinners: List<Task>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
