package com.suai.sergey.investmentportfolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataClassAdapter internal constructor(private val dataClassList: ArrayList<DataClass>):
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
        val dataClass: DataClass = this.dataClassList[position]
        holder.shortName.text = dataClass.getCost()
        holder.longName.text = dataClass.getLongName()
        holder.cost.text = dataClass.getCost()
    }

    class DataClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val shortName: TextView = itemView.findViewById(R.id.item_tv_short_name)
        val longName: TextView = itemView.findViewById(R.id.item_tv_long_name)
        val cost: TextView = itemView.findViewById(R.id.item_tv_cost)
    }
}