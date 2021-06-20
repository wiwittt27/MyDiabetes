package com.alawiyaa.mydiabetes.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.ActivityLoginBinding
import com.alawiyaa.mydiabetes.ui.HomeActivity
import com.alawiyaa.mydiabetes.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {


    private  val loginViewModel: LoginViewModel by viewModels()
    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)

        if (userRepository.isUserLogin()) {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

        loginViewModel.isLoading.observe(this, {
            binding?.pbLoading?.visibility = if (it) View.VISIBLE else View.GONE
        })

        loginViewModel.toastText.observe(this,{
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        loginViewModel.toMainActivity.observe(this,{

            val toMain  =Intent(this,HomeActivity::class.java); if (it) startActivity(toMain)
            finish()
        })

        binding?.btnLogin?.setOnClickListener(this)
        binding?.btnRegister?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login ->{
                val username = binding?.edtUsername?.text.toString().trim()
                val password = binding?.edtPassword?.text.toString().trim()
                loginViewModel.userLogin(username,password)
                userRepository.loginUser(username) // SAVE USER Login
            }
            R.id.btn_register ->{
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

}