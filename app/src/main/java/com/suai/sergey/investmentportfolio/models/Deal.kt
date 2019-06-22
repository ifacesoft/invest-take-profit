package com.suai.sergey.investmentportfolio.models

import androidx.room.*
import com.suai.sergey.investmentportfolio.converters.Converter
import java.util.*

@Entity(
    tableName = "invest_stock"
)
data class Deal internal constructor(
    @PrimaryKey(autoGenerate = true) private var id: Int?,
    @ColumnInfo(name = "deal_stock_uid") private var deal_stock_uid: String,
    @ColumnInfo(name = "deal_date")
    @TypeConverters(Converter::class) private var deal_date: Date = Date(),
    @ColumnInfo(name = "deal_price_per_stock") private var deal_price_per_stock: Double = 0.0,
    @ColumnInfo(name = "deal_stock_amount") private var deal_stock_amount: Int = 0,
    @ColumnInfo(name = "deal_income") private var deal_income: Double = 0.00,
    @ColumnInfo(name = "deal_expenses") private var deal_expenses: Double = 0.00
) {

    fun setId(value: Int) {
        id = value
    }

    fun getId(): Int? {
        return id
    }


    fun setDeal_stock_uid(value: String) {
        deal_stock_uid = value
    }

    fun getDeal_stock_uid(): String {
        return deal_stock_uid
    }

    fun setDeal_date(value: Date) {
        deal_date = value
    }

    fun getDeal_date(): Date {
        return deal_date
    }

    fun setDeal_price_per_stock(value: Double) {
        deal_price_per_stock = value
    }

    fun getDeal_price_per_stock(): Double {
        return deal_price_per_stock
    }


    fun setDeal_stock_amount(value: Int) {
        deal_stock_amount = value
    }

    fun getDeal_stock_amount(): Int {
        return deal_stock_amount
    }


    fun setDeal_income(value: Double) {
        deal_income = value
    }

    fun getDeal_income(): Double {
        return deal_income
    }

    fun setDeal_expenses(value: Double) {
        deal_expenses = value
    }

    fun getDeal_expenses(): Double {
        return deal_expenses
    }


}