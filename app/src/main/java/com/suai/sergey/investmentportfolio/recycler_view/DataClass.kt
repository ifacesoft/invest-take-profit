package com.suai.sergey.investmentportfolio.recycler_view

data class DataClass constructor(
    private var image: String,
    private var shortName: String,
    private var longName: String,
    private var cost: String
){
    fun setImage(value: String) {
        image = value
    }

    fun getImage(): String{
        return image
    }


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