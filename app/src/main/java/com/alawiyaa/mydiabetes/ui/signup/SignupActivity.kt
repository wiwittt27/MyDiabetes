package com.alawiyaa.mydiabetes.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alawiyaa.mydiabetes.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private var _binding : ActivitySignupBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        supportActionBar?.elevation = 0f
    }



}