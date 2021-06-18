package com.alawiyaa.mydiabetes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TWO_SECOND_IN_MILLIS = 3000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, TWO_SECOND_IN_MILLIS)
    }
}