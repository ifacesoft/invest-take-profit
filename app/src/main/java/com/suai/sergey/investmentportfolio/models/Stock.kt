package com.suai.sergey.investmentportfolio.models


import androidx.annotation.NonNull
import androidx.room.*
import com.suai.sergey.investmentportfolio.converters.Converter
import java.util.*

@Entity(
    tableName = "invest_stock"
    , indices = arrayOf(Index(value = ["stock_uid"], unique = true))
)
data class Stock internal constructor(
    @PrimaryKey(autoGenerate = true) private var id: Int?,
    @ColumnInfo(name = "stock_uid") private var stock_uid: String,
    @ColumnInfo(name = "stock_name") private var stock_name: String,
    @ColumnInfo(name = "stock_price_date")
    @TypeConverters(Converter::class) private var stock_price_date: Date = Date(),
    @ColumnInfo(name = "stock_price") private var stock_price: Double = 0.0,
    @ColumnInfo(name = "stock_price_min") private var stock_price_min: Double = 0.0,
    @ColumnInfo(name = "stock_price_max") private var stock_price_max: Double = 0.0
) {

    fun setId(value: Int) {
        id = value
    }

    fun getId(): Int? {
        return id
    }

    fun setStock_name(value: String) {
        stock_name = value
    }

    fun getStock_name(): String {
        return stock_name
    }

    fun setStock_uid(value: String) {
        stock_uid = value
    }

    fun getStock_uid(): String {
        return stock_uid
    }


    fun setStock_price(value: Double) {
        stock_price = value
    }

    fun getStock_price(): Double {
        return stock_price
    }

    fun setStock_price_min(value: Double) {
        stock_price_min = value
    }

    fun getStock_price_max(): Double {
        return stock_price_max
    }

    fun setStock_price_max(value: Double) {
        stock_price_max = value
    }

    fun getStock_price_min(): Double {
        return stock_price_max
    }

    fun setStock_price_date(value: Date) {
        stock_price_date = value
    }

    fun getStock_price_date(): Date {
        return stock_price_date
    }


}