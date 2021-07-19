package com.alawiyaa.mydiabetes.ui.signup.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus

class LoginViewModel(private val mDiabetesRepository: DiabetesRepository) : ViewModel() {
    fun userLogin(
                      username: String,
                      password: String) : LiveData<ApiResponse<ResponseStatus>> = mDiabetesRepository.userLogin(username,password)

}