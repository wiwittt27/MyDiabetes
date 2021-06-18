package com.alawiyaa.mydiabetes.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toMainActivity = MutableLiveData<Boolean>()
    val toMainActivity: LiveData<Boolean> = _toMainActivity

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    fun userLogin(username: String,password:String){
        _isLoading.value =true
        val client = ApiConfig.getApiService().loginUser(username,password)
        client.enqueue(object  : Callback<ResponseStatus>{
            override fun onResponse(call: Call<ResponseStatus>, response: Response<ResponseStatus>) {
               when{
                   response.isSuccessful ->{
                       _isLoading.value =false
                       _toastText.value = response.body()?.status
                   }
                   else ->{
                       _isLoading.value =false
                       _toastText.value = response.body()?.status
                   }

               }
            }

            override fun onFailure(call: Call<ResponseStatus>, t: Throwable) {
                _isLoading.value =false
                Log.d("STATUS_RESPONSE", "Failed! ${t.message.toString()}")
            }
        })

    }

}