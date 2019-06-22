package com.suai.sergey.investmentportfolio.repositories

import com.suai.sergey.investmentportfolio.entity.StockApi
import com.suai.sergey.investmentportfolio.entity.StockPriceApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InvestApi {
    @GET("/securities")
    fun getStocks(@Query("base") resourceName: String): Call<StockApi>


    @GET("/current_price/{id}")
    fun getStockPrice(@Path("id") id: String): Call<StockPriceApi>
//    {"title":"Текущая цена акции","type":"object","status":"200","error":"","limit":0,"offset":0,"count":1,"data":{"security":"ALRS","datetime":"2019-01-31 12:19:00","price":98.04}}
}
