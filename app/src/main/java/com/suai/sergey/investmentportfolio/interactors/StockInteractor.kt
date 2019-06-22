package com.suai.sergey.investmentportfolio.interactors

import android.util.Log
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication
import com.suai.sergey.investmentportfolio.entity.Stock
import com.suai.sergey.investmentportfolio.entity.StockApi
import com.suai.sergey.investmentportfolio.exceptions.StocksLoadApiFailException
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class StockInteractor : Observable() {
    private val TAG = "Invest_StockInteractor"


    val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    // may throw Exception
    suspend fun getTasks(): Deferred<String> = coroutineScope {
        // (1)
        async {
            return@async ("WHAT IS THAT?")
        }
    }

    fun loadData() = scope.launch {
        // (2)
        try {

            notifyObservers(loadFromApi())
            this@StockInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    private fun loadFromApi() {
        val stockInteractor: StockInteractor = this;
        InvestTakeProfitApplication.api!!.getStocks("TASK").enqueue(
            object : Callback<StockApi> {

                override fun onFailure(call: Call<StockApi>, t: Throwable) {
                    throw t
                }

                override fun onResponse(call: Call<StockApi>, response: Response<StockApi>) {
                    if (response.code() == 200) {
                        val stockResponse: StockApi = response.body()
                            ?: throw StocksLoadApiFailException("Ошибка получения Taks из api, пустой список")
                        if (stockResponse.data.isEmpty()) {
                            throw StocksLoadApiFailException("Пустой список data")
                        }

                        //положили в базу и обсервер уведомили, отправили ему массив тасков
                        stockInteractor.updateObservers(stockInteractor.saveTasks(stockResponse.data))
                    }

                    if (response.code() == 404) {
                        //throw TasksLoadApiFailException("Не найдено ничего!")
                    }
                }
            }
        )
    }

    private fun saveTasks(stockResponse: List<Stock>): List<com.suai.sergey.investmentportfolio.models.Stock> {
        // пусть так
        val stockEntities: ArrayList<com.suai.sergey.investmentportfolio.models.Stock> = ArrayList()

        //TODO Вопрос с getId
        for (stock in stockResponse) {
            stockEntities.add(
                com.suai.sergey.investmentportfolio.models.Stock(
                    null,
                    stock.secid.toString(),
                    stock.name.toString()
                )
            )
        }

        InvestTakeProfitApplication.roomDb!!.stockDao().insertAllStocks(stockEntities)
        return InvestTakeProfitApplication.roomDb!!.stockDao().getAllStocks()

    }

    //
    fun updateObservers(parcel: List<com.suai.sergey.investmentportfolio.models.Stock>) {
        Log.d(TAG, "UpdateObservers")
        setChanged()
        notifyObservers(parcel)
    }


}

