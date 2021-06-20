package com.alawiyaa.mydiabetes.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.databinding.ActivityLoginBinding
import com.alawiyaa.mydiabetes.databinding.ActivityRegisterBinding
import com.alawiyaa.mydiabetes.ui.login.LoginViewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding : ActivityRegisterBinding? = null
    private val binding get() = _binding

    private  val registerViewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        registerViewModel.isLoading.observe(this,{
            binding?.pbLoading?.visibility = if (it) View.VISIBLE else View.GONE
        })

        registerViewModel.toastText.observe(this,{
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        registerViewModel.finish.observe(this,{
            if (it) finish()
        })

        ArrayAdapter.createFromResource(this,R.array.gender_array, android.R.layout.simple_spinner_item).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.spinGender?.adapter = adapter

            binding?.btnRegisterRegist?.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
       if(v?.id == R.id.btn_register_regist){
           val name = binding?.edtFullName?.text.toString().trim()
           val gender = binding?.spinGender?.selectedItem.toString()
           val username = binding?.edtUsername?.text.toString().trim()
           val password = binding?.edtPassword?.text.toString().trim()

           when {
               name.isEmpty() -> {
                   binding?.edtFullName?.error = getString(R.string.empty)
               }
               username.isEmpty() -> {
                   binding?.edtUsername?.error = getString(R.string.empty)
               }
               password.isEmpty() -> {
                   binding?.edtPassword?.error = getString(R.string.empty)
               }
               else -> {
                   registerViewModel.registerUser(name,gender,username,password)
               }
           }

       }
    }
}