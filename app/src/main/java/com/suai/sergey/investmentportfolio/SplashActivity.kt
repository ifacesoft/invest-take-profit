package com.suai.sergey.investmentportfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suai.sergey.investmentportfolio.fragments.SellDialogFragment
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imageView.setOnClickListener {
            SellDialogFragment().show(supportFragmentManager, "a")
        }
    }
}
