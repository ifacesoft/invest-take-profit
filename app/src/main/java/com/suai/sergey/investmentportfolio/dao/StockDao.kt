package com.suai.sergey.investmentportfolio.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suai.sergey.investmentportfolio.models.Stock

@Dao
interface StockDao {
    @Query("SELECT * FROM invest_stock")
    fun getAllStocks(): List<Stock>

    @Insert
    @JvmSuppressWildcards
    fun insertAllStocks(objects: List<Stock>)
}