package com.suai.sergey.investmentportfolio.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suai.sergey.investmentportfolio.R
import com.suai.sergey.investmentportfolio.models.Stock

class DataClassAdapter internal constructor(private val dataClassList: ArrayList<Stock>) :
    RecyclerView.Adapter<DataClassAdapter.DataClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataClassViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_recycler_view, parent, false)
        return DataClassViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataClassList.size
    }

    override fun onBindViewHolder(holder: DataClassViewHolder, position: Int) {
        val dataClass: Stock = this.dataClassList[position]
        holder.shortName.text = dataClass.getStock_uid()
        holder.longName.text = dataClass.getStock_name()
        holder.cost.text = dataClass.getStock_price().toString()
    }

    fun refreshItem() {

    }

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        dataClassList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    class DataClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shortName: TextView = itemView.findViewById(R.id.item_tv_short_name)
        val longName: TextView = itemView.findViewById(R.id.item_tv_long_name)
        val cost: TextView = itemView.findViewById(R.id.item_tv_cost)
    }
}