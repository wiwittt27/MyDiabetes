package com.alawiyaa.mydiabetes.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alawiyaa.mydiabetes.data.source.remote.network.ApiConfig
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import com.alawiyaa.mydiabetes.data.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getListNews(type :String) : LiveData<ApiResponse<List<ResponseNewsItem>>>{
        EspressoIdlingResource.increment()
        val resultNews = MutableLiveData<ApiResponse<List<ResponseNewsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = ApiConfig.getApiService().getNewsDiabetes(type).await()
                resultNews.postValue(ApiResponse.success(response))
            }catch (e : IOException){
                e.printStackTrace()
                resultNews.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultNews
    }


    fun registerUser(name : String,gender : String, username : String, password:String) : LiveData<ApiResponse<ResponseStatus>>{
        EspressoIdlingResource.increment()
        val resultResponse = MutableLiveData<ApiResponse<ResponseStatus>>()
        CoroutineScope(IO).launch {
            try {
                val response = ApiConfig.getApiService().registerUser(name,gender,username,password).await()
                resultResponse.postValue(ApiResponse.success(response))
            }catch (e :IOException){
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        ResponseStatus()
                    )
                )
            }

        }
        EspressoIdlingResource.decrement()
        return resultResponse

    }

    fun loginUser(username: String,password:String) : LiveData<ApiResponse<ResponseStatus>> {
        EspressoIdlingResource.increment()
        val resultResponse = MutableLiveData<ApiResponse<ResponseStatus>>()

        CoroutineScope(IO).launch {
            try {
                val response = ApiConfig.getApiService().loginUser(username,password).await()
                resultResponse.postValue(ApiResponse.success(response))
            }catch (e :IOException){
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        ResponseStatus()
                    )
                )
            }

        }
        EspressoIdlingResource.decrement()
        return resultResponse
    }

    fun profileUser(username: String) : LiveData<ApiResponse<ResponseUser>> {
        val resultResponse = MutableLiveData<ApiResponse<ResponseUser>>()

        CoroutineScope(IO).launch {
            try {
                val response = ApiConfig.getApiService().getUserProfile(username).await()
                resultResponse.postValue(ApiResponse.success(response))
            }catch (e :IOException){
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        ResponseUser()
                    )
                )
            }

        }
        return resultResponse
    }

    fun resultDiagnosis(gender: String, polyuria:String, polydipsia:String,swl:String,weakness:String,polyphagia:String, gt:String,vb:String,itching:String,irritabiity:String,dh:String,pp:String,ms:String,alopecia:String,obesity:String) : LiveData<ApiResponse<ResponseClassification>> {
        val resultResponse = MutableLiveData<ApiResponse<ResponseClassification>>()

        CoroutineScope(IO).launch {
            try {
                val response = ApiConfig.getApiService().userClassification(gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity).await()
                resultResponse.postValue(ApiResponse.success(response))
            }catch (e :IOException){
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        ResponseClassification()
                    )
                )
            }

        }
        return resultResponse
    }
}