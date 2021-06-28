package com.alawiyaa.mydiabetes.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val isName: LiveData<String> = _name

    private val _gender = MutableLiveData<String>()
    val isGender: LiveData<String> = _gender

    private val _username = MutableLiveData<String>()
    val isUsername: LiveData<String> = _username

    private val _loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    fun getUserProfile(username:String){
        _loading.value  = true
        val client = ApiConfig.getApiService().getUserProfile(username)
        client.enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {

               when{
                   response.isSuccessful->{
                       _loading.value = false
                       _name.value = response.body()?.name
                       _gender.value = response.body()?.gender
                       _username.value = response.body()?.username
                   }else ->{
                   _loading.value = false
                   Log.d("STATUS_RESPONSE", "Failed! ${response.message()}")
                   }
               }

            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                _loading.value =false
                Log.d("STATUS_RESPONSE", "Failed! ${t.message.toString()}")
            }
        })
    }


}