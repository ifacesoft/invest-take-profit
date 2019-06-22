package com.suai.sergey.investmentportfolio.interactors

import android.util.Log
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication
import com.suai.sergey.investmentportfolio.entity.Stock
import com.suai.sergey.investmentportfolio.entity.StockApi
import com.suai.sergey.investmentportfolio.entity.StockPrice
import com.suai.sergey.investmentportfolio.entity.StockPriceApi
import com.suai.sergey.investmentportfolio.exceptions.StocksLoadApiFailException
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class StockPriceInteractor : Observable() {
    private val TAG = "Invest_StkPriceIntr"


    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    // may throw Exception
    suspend fun getTasks(): Deferred<String> = coroutineScope {
        // (1)
        async {
            return@async ("WHAT IS THAT?");
        }
    }

    fun loadData(uid: String) = scope.launch {
        // (2)
        try {

            notifyObservers(loadFromApi(uid))
            this@StockPriceInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    private fun loadFromApi(uid: String) {
        val stockPriceInteractor: StockPriceInteractor = this;
        InvestTakeProfitApplication.api!!.getStockPrice(uid).enqueue(
            object : Callback<StockPriceApi> {

                override fun onFailure(call: Call<StockPriceApi>, t: Throwable) {
                    throw t;
                }

                override fun onResponse(call: Call<StockPriceApi>, response: Response<StockPriceApi>) {
                    if (response.code() == 200) {
                        val stockResponse: StockPriceApi = response.body()
                            ?: throw StocksLoadApiFailException("Ошибка получения Taks из api, пустой список")
                        if (stockResponse.data == null) {
                            throw StocksLoadApiFailException("пустой")
                        }

                        //положили в базу и обсервер уведомили, отправили ему массив тасков
                        stockPriceInteractor.saveTasks(stockResponse.data!!)
                    }

                    if (response.code() == 404) {
                        //throw TasksLoadApiFailException("Не найдено ничего!")
                    }
                }
            }
        )
    }

    private fun saveTasks(stockResponse: StockPrice) {
        // пусть так
        val stockEntities: ArrayList<com.suai.sergey.investmentportfolio.models.Stock> = ArrayList()

        InvestTakeProfitApplication.roomDb!!.stockDao().getAllStocks()

        InvestTakeProfitApplication.roomDb!!.stockDao().updatePrice(
            stockResponse.security!!,
            stockResponse.price!!.toDouble(),
            stockResponse.datetime.toString()
        )
        Log.d(TAG, "UpdateObservers")
        setChanged()
        notifyObservers()
    }

}