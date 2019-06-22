package com.suai.sergey.investmentportfolio.dao

import androidx.room.*
import com.suai.sergey.investmentportfolio.models.Deal
import com.suai.sergey.investmentportfolio.models.Stock

@Dao
interface DealDao {
    @Query("SELECT * FROM invest_deal")
    fun getAllDeals(): List<Deal>


    @Query("SELECT * FROM invest_deal WHERE deal_stock_uid = :uid")
    fun getDeal(uid: String): Deal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertDeal(objects: Deal)
}