package com.suai.sergey.investmentportfolio.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.Toast
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.suai.sergey.investmentportfolio.InvestDataBase
import com.suai.sergey.investmentportfolio.MainActivity
import com.suai.sergey.investmentportfolio.R
import com.suai.sergey.investmentportfolio.entity.StockPriceApi
import com.suai.sergey.investmentportfolio.exceptions.StocksLoadApiFailException
import com.suai.sergey.investmentportfolio.repositories.InvestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.IBinder
import android.os.Binder
import com.suai.sergey.investmentportfolio.InvestTakeProfitApplication


class UpdateCurrentPrices : IntentService(UpdateCurrentPrices::class.simpleName) {
    val LOG_TAG = "myLogs"
    val ONGOING_NOTIFICATION_ID = 1

    private val mBinder = LocalBinder()
    private var activity = null

    var mAllowRebind: Boolean = false // indicates whether onRebind should be used

    inner class LocalBinder : Binder() {
        internal// Return this instance of LocalService so clients can call public methods
        val service: UpdateCurrentPrices
            get() = this@UpdateCurrentPrices
    }

    override fun onHandleIntent(intent: Intent) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://moex.ifacesoft.ru") //Базовая часть адреса
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build()

        val api =
            retrofit!!.create(InvestApi::class.java) //Создаем объект, при помощи которого будем выполнять запросы

        val roomDb = Room.databaseBuilder(this.applicationContext, InvestDataBase::class.java, "invest")
            .allowMainThreadQueries()
            .build()

        val label = intent.getStringExtra("label")
        Log.d(LOG_TAG, "onHandleIntent start $label")

        val builder = getBuilder()

        while (true) {
            synchronized(this) {
                try {
                    val favoriteStocks = roomDb.stockDao().getFavoriteStocks()

                    for (stock in favoriteStocks) {
                        Log.d(LOG_TAG, "onHandleIntent getStockPrices " + System.currentTimeMillis())

                        api.getStockPrice(stock.getStock_uid()).enqueue(
                            object : Callback<StockPriceApi> {
                                override fun onFailure(call: Call<StockPriceApi>, t: Throwable) {

                                    throw t
                                }

                                override fun onResponse(call: Call<StockPriceApi>, response: Response<StockPriceApi>) {
                                    if (response.code() == 200) {
                                        val stockPriceApi: StockPriceApi = response.body()
                                            ?: throw StocksLoadApiFailException("Ошибка получения Taks из api, пустой список")
                                        if (stockPriceApi.data == null) {
                                            throw StocksLoadApiFailException("пустой")
                                        }

                                        val stockPrice = stockPriceApi.data

                                        Log.d(LOG_TAG, "onHandleIntent price " + stockPrice.toString())

                                        roomDb.stockDao().updatePrice(
                                            stockPrice?.security!!,
                                            stockPrice.price!!.toDouble(),
                                            stockPrice.datetime.toString()
                                        )

                                        InvestTakeProfitApplication.observableStocks.update = true
                                    }

                                    if (response.code() == 404) {
                                        //throw TasksLoadApiFailException("Не найдено ничего!")
                                    }
                                }
                            }
                        )
                    }

                    NotificationManagerCompat.from(this).apply {
                        builder
                            .setContentTitle("Цены на акции")
                            .setContentText("Объявлен год роста акций ЛУКОЙЛа: " + System.currentTimeMillis())

                        notify(ONGOING_NOTIFICATION_ID, builder.build())
                        Log.d(LOG_TAG, "onHandleIntent notify " + System.currentTimeMillis())
                    }

                    Thread.sleep(1000)
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startForeground(ONGOING_NOTIFICATION_ID, getBuilder().build())

        Toast.makeText(applicationContext, "Наченаю следить за ценами.", Toast.LENGTH_LONG).show()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    private fun getBuilder(): NotificationCompat.Builder {
        var CHANNEL_ID = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "stock_service_channel"
            val channelName = "stock_service_channel_name"

            val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE)
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)
            CHANNEL_ID = channelId
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ice62b)
            .setContentIntent(pendingIntent)
            .setContentTitle("Цены на акции")
            .setContentText("Объявлен год роста акций ЛУКОЙЛа")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    override fun onBind(intent: Intent): IBinder? {
        // A client is binding to the service with bindService()
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        // All clients have unbound with unbindService()
        return mAllowRebind
    }

    override fun onRebind(intent: Intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
}
