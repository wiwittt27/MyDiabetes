package com.alawiyaa.mydiabetes.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import com.alawiyaa.mydiabetes.vo.Resource

interface DiabetesDataSource {

   fun getListNews(type :String) : LiveData<Resource<PagedList<NewsEntity>>>

   fun userRegister(name : String,gender : String, username : String, password:String) : LiveData<ApiResponse<ResponseStatus>>

   fun userLogin( username : String, password:String) : LiveData<ApiResponse<ResponseStatus>>

   fun profileUser( username : String) : LiveData<ApiResponse<ResponseUser>>

   fun resultDiagnosis(age :String,gender: String, polyuria:String, polydipsia:String,swl:String,weakness:String,polyphagia:String, gt:String,vb:String,itching:String,irritabiity:String,dh:String,pp:String,ms:String,alopecia:String,obesity:String) : LiveData<ApiResponse<ResponseClassification>>

    fun getDetailNews(newsId : Int) : LiveData<NewsEntity>

    fun setFavoriteNews(news: NewsEntity)

    fun getListFavoriteNews(): LiveData<PagedList<NewsEntity>>

    fun getUserByUsername(username :String) : LiveData<PagedList<UserDiseaseEntity>>

    fun insertResult(user : UserDiseaseEntity)

    fun deleteResult(user : UserDiseaseEntity)

}