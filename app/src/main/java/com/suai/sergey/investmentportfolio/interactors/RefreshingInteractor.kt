package com.suai.sergey.investmentportfolio.interactors

import android.util.Log
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication
import com.suai.sergey.investmentportfolio.models.Stock
import kotlinx.coroutines.*
import java.util.*

class RefreshingInteractor : Observable() {
    private val TAG = "Invest_RfrsPriceIntr"

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    // may throw Exception
    suspend fun getTasks(): Deferred<String> = coroutineScope {
        // (1)
        async {
            return@async ("WHAT IS THAT?");
        }
    }

    fun loadData() = scope.launch {
        // (2)
        try {
            notifyObservers(refreshFromDb())
            this@RefreshingInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    private fun refreshFromDb() {
        // пусть та
        Log.d(TAG, "UpdateObservers")
        var list: List<Stock> = InvestTakeProfitApplication.roomDb!!.stockDao().getStocksWithPrices()
        setChanged()
        notifyObservers(InvestTakeProfitApplication.roomDb!!.stockDao().getStocksWithPrices())
    }
}