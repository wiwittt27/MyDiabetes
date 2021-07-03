package com.alawiyaa.mydiabetes.ui.signup.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.remote.StatusResponse
import com.alawiyaa.mydiabetes.data.utils.SessionManager
import com.alawiyaa.mydiabetes.data.utils.UserRepository
import com.alawiyaa.mydiabetes.databinding.FragmentLoginBinding
import com.alawiyaa.mydiabetes.ui.home.HomeActivity
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory


class LoginFragment : Fragment(), View.OnClickListener {


    private  lateinit var loginViewModel: LoginViewModel
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding
    private lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = DiabetesViewModelFactory.getInstance(requireContext())
            loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
            val sesi = SessionManager(requireContext())
            userRepository = UserRepository.getInstance(sesi)

            if (userRepository.isUserLogin()) {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
                activity?.finish()
            }


            binding?.btnLogin?.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login ->{
                binding?.pbLoading?.visibility = View.VISIBLE
                val username = binding?.edtUsername?.text.toString().trim()
                val password = binding?.edtPassword?.text.toString().trim()
                loginViewModel.userLogin(username,password).observe(this,{status->
                    if (status != null){
                        when(status.status){
                            StatusResponse.SUCCESS ->{
                                if (status.body?.status.equals("Sukses Login!")){
                                   binding?.pbLoading?.visibility = View.GONE
                                   Toast.makeText(requireContext(), "${status.body?.status}", Toast.LENGTH_SHORT).show()
                                   startActivity(Intent(requireContext(), HomeActivity::class.java))
                                   activity?.finish()
                                }else if(status.body?.status.equals("Login gagal!")){
                                    binding?.pbLoading?.visibility = View.GONE
                                    Toast.makeText(requireContext(), "${status.body?.status}", Toast.LENGTH_SHORT).show()
                                }
                            }
                            StatusResponse.ERROR->{
                                binding?.pbLoading?.visibility = View.GONE
                                Toast.makeText(requireContext(), "Periksa koneksi internet anda!", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                })
                userRepository.loginUser(username) // SAVE USER Login
            }

        }
    }
}