package com.suai.sergey.investmentportfolio.models


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invest_stock")
data class Stock internal constructor(
    @PrimaryKey(autoGenerate = true) private var id: Int,
    @ColumnInfo(name = "stock_name") private var stock_name: String,
    @ColumnInfo(name = "stock_uid") private var stock_uid: String
) {

    fun setId(value: Int) {
        id = value
    }

    fun getId(): Int {
        return id
    }

    fun setStock_name(value: String) {
        stock_name = value
    }

    fun getStock_name(): String {
        return stock_name
    }

    fun setStock_uid(value: String) {
        stock_name = value
    }

    fun getStock_uid(): String {
        return stock_name
    }


}