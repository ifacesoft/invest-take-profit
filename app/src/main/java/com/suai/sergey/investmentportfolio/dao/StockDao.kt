package com.suai.sergey.investmentportfolio.dao

import androidx.room.Dao
import androidx.room.Query
import com.suai.sergey.investmentportfolio.models.Stock
@Dao
interface StockDao {
    @Query("SELECT * FROM invest_stock")
    fun getAllStock(): List<Stock>


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertStock(stock: Stock)
//
//    @Insert
//    @JvmSuppressWildcards
//    fun insertAll(objects: List<Stock>)
}