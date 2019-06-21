package com.suai.sergey.investmentportfolio

data class DataClass constructor(
    private var shortName: String,
    private var longName: String,
    private var cost: String
){
    fun setShortName(value: String){
        shortName = value
    }
    fun getShortName(): String {
        return shortName
    }

    fun setLongName(value: String){
        longName = value
    }
    fun getLongName(): String{
        return longName
    }
    fun setCost(value: String){
        cost = value
    }
    fun getCost(): String{
        return cost
    }
}