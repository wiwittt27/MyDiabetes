package com.alawiyaa.mydiabetes.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    private val _finish = MutableLiveData<Boolean>()
    val finish : LiveData<Boolean> = _finish

    fun registerUser(name : String,gender : String, username : String, password:String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().registerUser(name,gender,username,password)
        client.enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                _isLoading.value = false
                when{
                    response.isSuccessful->{
                        _toastText.value = response.body()?.status
                        _finish.value = true
                    }else ->{
                        _toastText.value = response.body()?.status
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _isLoading.value = false
                Log.e("STATUS_RESPONSE", "onFailure: ${t.message.toString()}")
            }
        })
    }

}