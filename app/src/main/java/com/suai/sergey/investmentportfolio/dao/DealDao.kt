package com.suai.sergey.investmentportfolio.dao

import androidx.room.*
import com.suai.sergey.investmentportfolio.models.Deal
import com.suai.sergey.investmentportfolio.models.Stock

@Dao
interface DealDao {
    @Query("SELECT * FROM invest_deal")
    fun getAllStocks(): List<Deal>

    @Query("SELECT * FROM invest_stock")
    fun getStock(): Stock

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllStocks(objects: Stock)

    @Query("UPDATE invest_stock SET stock_price = :price, stock_price_date = :datetime WHERE stock_uid = :uid")
    fun updatePrice(uid: String, price: Double, datetime: String)

    @Query("SELECT * FROM invest_stock WHERE stock_price <> 0.00")
    fun getStocksWithPrices(): List<Stock>

    @Update
    fun updateStockPrice(stock: Stock)

    @Query("SELECT * FROM invest_stock WHERE stock_price IS NULL")
    fun getFavoriteStocks(): List<Stock>

    //delete добавить
}