package com.suai.sergey.investmentportfolio.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class StockApi {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("error")
    @Expose
    var error: String? = null
    @SerializedName("limit")
    @Expose
    var limit: Int? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("data")
    @Expose
    var data: List<Stock> = ArrayList<Stock>()

}