package com.suai.sergey.investmentportfolio.dao

import androidx.room.*
import com.suai.sergey.investmentportfolio.models.Stock

@Dao
interface StockDao {
    @Query("SELECT * FROM invest_stock")
    fun getAllStocks(): List<Stock>

    @Query("SELECT * FROM invest_stock")
    fun getStock(): Stock

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllStocks(objects: List<Stock>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllStocks(objects: Stock)

    @Query("UPDATE invest_stock SET stock_price = :price, stock_price_date = :datetime WHERE stock_uid = :uid")
    fun updatePrice(uid: String, price: Double, datetime: String)

    @Query("SELECT * FROM invest_stock WHERE stock_price <> 0.00")
    fun getStocksWithPrices(): List<Stock>

    @Update
    fun updateStockPrice(stock: Stock)

    @Query("SELECT * FROM invest_stock WHERE stock_price <> 0.00")
    fun getFavoriteStocks(): List<Stock>

    @Query("SELECT * FROM invest_stock WHERE stock_price <> 0.00")
    fun observeStocksWithPrices(): List<Stock>
}