package com.suai.sergey.investmentportfolio

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.facebook.stetho.Stetho
import com.suai.sergey.investmentportfolio.converters.Converter
import com.suai.sergey.investmentportfolio.dao.StockDao
import com.suai.sergey.investmentportfolio.models.Stock
import com.suai.sergey.investmentportfolio.repositories.InvestApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InvestTakeProfitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //инциализурру
        Stetho.initializeWithDefaults(this);

        val retrofit = Retrofit.Builder()
            .baseUrl("http://moex.ifacesoft.ru") //Базовая часть адреса
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build()

        api =
            retrofit!!.create(InvestApi::class.java) //Создаем объект, при помощи которого будем выполнять запросы

        roomDb = Room.databaseBuilder(this.applicationContext, InvestDataBase::class.java, "invest")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var api: InvestApi? = null
            private set
        var roomDb: InvestDataBase? = null
            private set
    }
}

//@TypeConverters(Converters::class)
@Database(
    entities = [Stock::class],
    version = 1
)

@TypeConverters(Converter::class)
abstract class InvestDataBase : RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        private var INSTANCE: InvestDataBase? = null
        fun getDatabase(context: Context): InvestDataBase? {
            if (INSTANCE == null) {
                synchronized(InvestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        InvestDataBase::class.java, "chapter.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}