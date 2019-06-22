package com.suai.sergey.investmentportfolio.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color

import android.widget.Toast
import android.os.Build
import androidx.core.app.NotificationCompat
import com.suai.sergey.investmentportfolio.MainActivity
import com.suai.sergey.investmentportfolio.R


class UpdateCurrentPrices : IntentService(UpdateCurrentPrices::class.simpleName) {
    val SERVICE_START = "SERVICE_START"

    val SERVICE_STOP = "SERVICE_STOP"

    override fun onHandleIntent(intent: Intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        val endTime = System.currentTimeMillis() + 5 * 1000
        while (System.currentTimeMillis() < endTime) {
            synchronized(this) {
                try {
                    Thread.sleep(endTime - System.currentTimeMillis())

                } catch (e: Exception) {
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        var CHANNEL_ID: String = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "stock_service_channel"
            val channelName = "stock_service_channel_name"

            val chan = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_NONE)
            chan.lightColor = Color.BLUE
            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)
            CHANNEL_ID = channelId
        }

        val ONGOING_NOTIFICATION_ID = 1

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ice62b)
            .setContentTitle("Цены на акции")
            .setContentText("Объявлен год роста акций ЛУКОЙЛа")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        startForeground(ONGOING_NOTIFICATION_ID, builder.build())

        Toast.makeText(applicationContext, "Foreground service is started.", Toast.LENGTH_LONG).show()

        return super.onStartCommand(intent, flags, startId)
    }
}
