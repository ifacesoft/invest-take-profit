package com.suai.sergey.investmentportfolio.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StockPrice {

    @SerializedName("security")
    @Expose
    var security: String? = null
    @SerializedName("datetime")
    @Expose
    var datetime: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null

}