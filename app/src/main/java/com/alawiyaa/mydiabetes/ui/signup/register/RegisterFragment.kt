package com.alawiyaa.mydiabetes.ui.signup.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.R
import com.alawiyaa.mydiabetes.data.source.remote.StatusResponse
import com.alawiyaa.mydiabetes.databinding.FragmentRegisterBinding
import com.alawiyaa.mydiabetes.viewmodel.DiabetesViewModelFactory


class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding

    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = DiabetesViewModelFactory.getInstance(requireContext())
            registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]


            ArrayAdapter.createFromResource(requireContext(),R.array.gender_array, android.R.layout.simple_spinner_item).also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding?.spinGender?.adapter = adapter


            }
            binding?.btnRegisterRegist?.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_register_regist){
            val name = binding?.edtFullName?.text.toString().trim()
            val gender = binding?.spinGender?.selectedItem.toString()
            val username = binding?.edtRegisterUsername?.text.toString().trim()
            val password = binding?.edtRegisterPassword?.text.toString().trim()

            when {
                name.isEmpty() -> {
                    binding?.edtFullName?.error = getString(R.string.empty)
                }
                username.isEmpty() -> {
                    binding?.edtRegisterUsername?.error = getString(R.string.empty)
                }
                password.isEmpty() -> {
                    binding?.edtRegisterPassword?.error = getString(R.string.empty)
                }
                else -> {
                    binding?.pbLoading?.visibility = View.VISIBLE
                    registerViewModel.registerUser(name,gender,username,password).observe(this,{status->

                        if (status != null){
                            when(status.status){
                                StatusResponse.SUCCESS ->{
                                    binding?.pbLoading?.visibility = View.GONE
                                    Toast.makeText(requireContext(), "${status.body?.status}", Toast.LENGTH_SHORT).show()

                                }
                                StatusResponse.ERROR->{
                                    binding?.pbLoading?.visibility = View.GONE
                                    Toast.makeText(requireContext(), "Periksa koneksi internet anda!", Toast.LENGTH_SHORT).show()                                }

                            }
                        }

                    })
                }
            }

        }
    }
}