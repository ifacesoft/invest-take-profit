package com.suai.sergey.investmentportfolio.adapters

import android.view.ViewGroup
import android.R.attr.keySet
import android.view.View
import android.widget.BaseAdapter


class HashMapAdapter(data: HashMap<String, String>) : BaseAdapter() {

    private var mData = HashMap<String, String>()
    private val mKeys: Array<String>

    init {
        mData = data
        mKeys = mData.keys.toTypedArray()
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): String? {
        return mData[mKeys.get(position)]
    }

    override fun getItemId(arg0: Int): Long {
        return arg0.toLong()
    }

    override fun getView(pos: Int, convertView: View, parent: ViewGroup): View {
        val key = mKeys[pos]
        val Value = getItem(pos).toString()

        //do your view stuff here

        return convertView
    }
}