package com.alawiyaa.mydiabetes.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.ui.signup.SignupActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TWO_SECOND_IN_MILLIS = 3000L
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }, TWO_SECOND_IN_MILLIS)
    }
}