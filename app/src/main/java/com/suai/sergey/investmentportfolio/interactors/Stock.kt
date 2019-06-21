package com.suai.sergey.investmentportfolio.interactors

import android.util.Log
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication
import com.suai.sergey.investmentportfolio.entity.StockApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class StockInteractor : Observable() {
    private val TAG = "Invest_TaskInteractor"


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

            notifyObservers(loadFromApi())
            this@StockInteractor.getTasks().await()
        } catch (e: Exception) {
            Log.d(TAG, "Exception OMFG!")
            throw e
        }
    }

    private fun loadFromApi() {
        val taskInteractor: StockInteractor = this;
        InvestTakeProfitApplication.api!!.getStocks("TASK").enqueue(
            object : Callback<StockApi> {

                override fun onFailure(call: Call<StockApi>, t: Throwable) {
                    throw t;
                }

                override fun onResponse(call: Call<StockApi>, response: Response<StockApi>) {
                    if (response.code() == 200) {
                        response.body()
                        // ?: throw TasksLoadApiFailException("Ошибка получения Taks из api, пустой список")

                        //положили в базу и обсервер уведомили, отправили ему массив тасков
                        // taskInteractor.updateObservers(taskInteractor.saveTasks(tasks))
                    }

                    if (response.code() == 404) {
                        //throw TasksLoadApiFailException("Не найдено ничего!")
                    }
                }
            }
        )
    }

//    private fun saveTasks(taskResponse: List<TaskResponse>): List<Task> {
//        // пусть так
//        val taskEntities: ArrayList<Task> = ArrayList()
//
//        //TODO Вопрос с getId
//        for (task in taskResponse) {
//            taskEntities.add(Task(task.id, task.taskName))
//        }
//
//        Repository.roomDb!!.taskDao().insertAll(taskEntities)
//        return Repository.roomDb!!.taskDao().getAllTasks()
//
//    }
//
//    fun updateObservers(parcel: List<Task>) {
//        Log.d(TAG, "Kourutines")
//        setChanged()
//        notifyObservers(parcel)
//    }


}

