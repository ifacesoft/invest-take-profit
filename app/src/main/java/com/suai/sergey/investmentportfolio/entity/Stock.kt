package com.suai.sergey.investmentportfolio.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Stock {

    @SerializedName("secid")
    @Expose
    var secid: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}