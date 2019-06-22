package com.suai.sergey.investmentportfolio.interactors

import android.util.Log
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication
import com.suai.sergey.investmentportfolio.models.Deal
import kotlinx.coroutines.*
import java.util.*

class BuyInteractor : Observable() {
    private val TAG = "Invest_BuyInteractr"

    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    // may throw Exception
    suspend fun getTasks(): Deferred<String> = coroutineScope {
        // (1)
        async {
            return@async ("WHAT IS THAT?");
        }
    }

    fun buyDeal() = scope.launch {
        // (2)
        try {
            makeDeal(true)
            this@BuyInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    fun sellDeal() = scope.launch {
        // (2)
        try {
            makeDeal(false)
            this@BuyInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    private fun makeDeal(flagBuy: Boolean) {
        val deal: Deal = if (flagBuy) {
            Deal(null, "SBER", Date(), 250.0, 10, (10 * 250.0), 0.00)
        } else {
            Deal(null, "SBER", Date(), 300.0, 10, 0.00, (10 * 300.0))
        }

        InvestTakeProfitApplication.roomDb!!.dealDao().insertDeal(deal)
        // пусть та
        Log.d(TAG, "UpdateObservers")
        setChanged()
        notifyObservers(InvestTakeProfitApplication.roomDb!!.dealDao().insertDeal(deal))
    }

}