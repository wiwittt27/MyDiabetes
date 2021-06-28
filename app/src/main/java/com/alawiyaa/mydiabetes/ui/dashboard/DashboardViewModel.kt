package com.alawiyaa.mydiabetes.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiService
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel :ViewModel() {

    private val listTypeDiabetes = MutableLiveData<ArrayList<ResponseNewsItem>>()
    private val listDrug = MutableLiveData<ArrayList<ResponseNewsItem>>()
    private val listFactorDiabetes = MutableLiveData<ArrayList<ResponseNewsItem>>()
    private val listFactorRisk = MutableLiveData<ArrayList<ResponseNewsItem>>()

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    fun getTypeDiabetes(type : String){
        val listDiabetes = ApiConfig.getApiService().getNewsDiabetes(type)
        listDiabetes.enqueue(object : Callback<ArrayList<ResponseNewsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseNewsItem>>,
                response: Response<ArrayList<ResponseNewsItem>>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    result?.let {
                        listTypeDiabetes.postValue(it)
                    }
                }else{
                    _toastText.value = "Gagal Load Item!"
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseNewsItem>>, t: Throwable) {
                Log.d("STATUS", " ${t.message}")
            }
        })
    }

    fun getTypeDrug(type : String){
        val listDiabetes = ApiConfig.getApiService().getNewsDiabetes(type)
        listDiabetes.enqueue(object : Callback<ArrayList<ResponseNewsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseNewsItem>>,
                response: Response<ArrayList<ResponseNewsItem>>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    result?.let {
                        listDrug.postValue(it)
                    }
                }else{
                    _toastText.value = "Gagal Load Item!"
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseNewsItem>>, t: Throwable) {
                Log.d("STATUS", " ${t.message}")
            }
        })
    }

    fun getTypeFactor(type : String){
        val listDiabetes = ApiConfig.getApiService().getNewsDiabetes(type)
        listDiabetes.enqueue(object : Callback<ArrayList<ResponseNewsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseNewsItem>>,
                response: Response<ArrayList<ResponseNewsItem>>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    result?.let {
                        listFactorDiabetes.postValue(it)
                    }
                }else{
                    _toastText.value = "Gagal Load Item!"
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseNewsItem>>, t: Throwable) {
                Log.d("STATUS", " ${t.message}")
            }
        })
    }

    fun getTypeFactorRisk(type : String){
        val listDiabetes = ApiConfig.getApiService().getNewsDiabetes(type)
        listDiabetes.enqueue(object : Callback<ArrayList<ResponseNewsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseNewsItem>>,
                response: Response<ArrayList<ResponseNewsItem>>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    result?.let {
                        listFactorRisk.postValue(it)
                    }
                }else{
                    _toastText.value = "Gagal Load Item!"
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseNewsItem>>, t: Throwable) {
                Log.d("STATUS", " ${t.message}")
            }
        })
    }

    fun getNewsDiabetes() : LiveData<ArrayList<ResponseNewsItem>> = listTypeDiabetes
    fun getNewsDrug() : LiveData<ArrayList<ResponseNewsItem>> = listDrug
    fun getNewsFactor() : LiveData<ArrayList<ResponseNewsItem>> = listFactorDiabetes
    fun getNewsFactorRisk() : LiveData<ArrayList<ResponseNewsItem>> = listFactorRisk

}