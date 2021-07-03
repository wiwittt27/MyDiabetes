package com.alawiyaa.mydiabetes.ui.signup.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus

class RegisterViewModel(private val mDiabetesRepository: DiabetesRepository) : ViewModel() {

    fun registerUser( name: String,
                      gender: String,
                      username: String,
                      password: String) : LiveData<ApiResponse<ResponseStatus>> = mDiabetesRepository.userRegister(name,gender,username,password)


}