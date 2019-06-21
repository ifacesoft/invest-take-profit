package com.suai.sergey.investmentportfolio

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suai.sergey.investmentportfolio.dao.StockDao
import com.suai.sergey.investmentportfolio.models.Stock
import com.suai.sergey.investmentportfolio.repositories.InvestApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InvestTakeProfitApplication : Application() {
    private var retrofit: Retrofit? = null

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
            .baseUrl("http://oplotbot.ashwilliams.tk") //Базовая часть адреса
            //   .addConverterFactory(createGsonConverter())
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build()

        api =
            retrofit!!.create<InvestApi>(InvestApi::class.java) //Создаем объект, при помощи которого будем выполнять запросы

        roomDb = Room.databaseBuilder(this.applicationContext, InvestDataBase::class.java, "invest")
            // allow queries on the main thread.
            // Don't do this on a real app! See PersistenceBasicSample for an example.
            .allowMainThreadQueries()
            .build()


    }

//    private fun createGsonConverter(): Converter.Factory {
//        val gsonBuilder = GsonBuilder()
//        gsonBuilder.registerTypeAdapter(Tasks::class.java, RatesDeserializer())
//        val gson = gsonBuilder.create()
//        return GsonConverterFactory.create(gson)
//    }

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
    //  Training::class, TrainingRepeatCount::class],
    version = 1
)
abstract class InvestDataBase : RoomDatabase() {
    abstract fun stockDao(): StockDao
    //abstract fun trainingDsao(): TrainingDao
    // abstract fun trainingRepeatCountDao(): TrainingRepeatCountDao

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