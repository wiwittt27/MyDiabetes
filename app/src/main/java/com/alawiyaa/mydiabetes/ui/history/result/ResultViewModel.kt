package com.alawiyaa.mydiabetes.ui.history.result

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.UserRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel(application: Application) : ViewModel(){

    private val mUserRepository: UserRepository = UserRepository(application)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toMainActivity = MutableLiveData<Boolean>()
    val toMainActivity: LiveData<Boolean> = _toMainActivity

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText


    fun userClassification(gender: String, polyuria:String, polydipsia:String,swl:String,weakness:String,polyphagia:String, gt:String,vb:String,itching:String,irritabiity:String,dh:String,pp:String,ms:String,alopecia:String,obesity:String){
        _isLoading.value =true
        val client = ApiConfig.getApiService().userClassification(gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity)
        client.enqueue(object : Callback<ResponseClassification>{
            override fun onResponse(
                call: Call<ResponseClassification>,
                response: Response<ResponseClassification>
            ) {
                when{
                    response.isSuccessful ->{
                        _isLoading.value =false
                        _resultText.value = response.body()?.hasil
                        _toMainActivity.value = true
                    }
                    else ->{
                        _isLoading.value =false
                        _resultText.value = "YNTKTS"
                    }

                }
            }

            override fun onFailure(call: Call<ResponseClassification>, t: Throwable) {
                _isLoading.value =false
                Log.d("STATUS_RESPONSE", "Failed! ${t.message.toString()}")
            }
        })
    }

    fun insert(user: UserDiseaseEntity) {
        mUserRepository.insert(user)
    }
    fun update(user: UserDiseaseEntity) {
        mUserRepository.update(user)
    }
    fun delete(user: UserDiseaseEntity) {
        mUserRepository.delete(user)
    }
    
    
}