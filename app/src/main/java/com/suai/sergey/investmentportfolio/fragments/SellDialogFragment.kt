package com.suai.sergey.investmentportfolio.fragments

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.suai.sergey.investmentportfolio.R
import kotlinx.android.synthetic.main.sell_dialog_fragment_layout.view.*

class SellDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_Alert)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sell_dialog_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.button.setOnClickListener {
            if (!view.amount.text?.isBlank()!! && !view.price.text?.isBlank()!!) {
                // ЗАПИСЫВАЕМ ПОКУПКУ В БАЗУ
                val amount: Int = view.amount.text.toString().toInt()
                val price: Float = view.price.text.toString().toFloat()
                onDestroyView()
            } else {
                // ВЫВЕСТИ ОШИБКУ
                Toast.makeText(activity, "Заполни все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}