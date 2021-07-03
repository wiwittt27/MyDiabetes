package com.alawiyaa.mydiabetes.ui.signup.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val mDiabetesRepository: DiabetesRepository) : ViewModel() {
    fun userLogin(
                      username: String,
                      password: String) : LiveData<ApiResponse<ResponseStatus>> = mDiabetesRepository.userLogin(username,password)

}