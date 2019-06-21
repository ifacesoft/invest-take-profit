package com.suai.sergey.investmentportfolio.fragments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suai.sergey.investmentportfolio.R
import kotlinx.android.synthetic.main.buy_dialog_fragment_layout.view.*

class BuyDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.buy_dialog_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.button.setOnClickListener {
            if (!view.amount.text.isBlank() && !view.price.text.isBlank()) {
                // ЗАПИСЫВАЕМ ПОКУПКУ В БАЗУ
            } else {
                // ВЫВЕСТИ ОШИБКУ (КАК ЛУЧШЕ РЕАЛИЗОВАТЬ?)
            }
        }
    }
}