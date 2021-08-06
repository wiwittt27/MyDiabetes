package com.alawiyaa.mydiabetes.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.local.LocalDataSource
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.RemoteDataSource
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import com.alawiyaa.mydiabetes.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeDiabetesRepository (private val remoteDataSource: RemoteDataSource,
                                                 private val localDataSource: LocalDataSource
) :DiabetesDataSource {





    override fun getListNews(type :String): LiveData<Resource<PagedList<NewsEntity>>> {
     return object : NetworkBoundResource<PagedList<NewsEntity>, List<ResponseNewsItem>>(){
         override fun shouldFetch(data: PagedList<NewsEntity>): Boolean =

         data.isEmpty() || data == null


         override fun saveCallResult(data: List<ResponseNewsItem>) {
             val newsList = ArrayList<NewsEntity>()
             for (item in data) {
                 val news = NewsEntity(
                     null,
                     item.id,
                     item.title,
                     item.description,
                     item.imagePath,
                     item.source,
                     item.type,
                     false
                 )
                 newsList.add(news)
             }
             localDataSource.insertNews(newsList)
         }

         override fun loadFromDB(): LiveData<PagedList<NewsEntity>>  {
             val config = PagedList.Config.Builder().apply {
                 setEnablePlaceholders(false)
                 setInitialLoadSizeHint(4)
                 setPageSize(4)
             }.build()
             return LivePagedListBuilder(localDataSource.getListNews(type), config).build()
         }


         override fun createCall(): LiveData<ApiResponse<List<ResponseNewsItem>>> =
             remoteDataSource.getListNews(type)

     }.asLiveData()
    }

    override fun userRegister(
        name: String,
        gender: String,
        username: String,
        password: String
    ): LiveData<ApiResponse<ResponseStatus>> =   remoteDataSource.registerUser(name,gender,username,password)

    override fun userLogin(
        username: String,
        password: String
    ): LiveData<ApiResponse<ResponseStatus>> = remoteDataSource.loginUser(username,password)

    override fun profileUser(username: String): LiveData<ApiResponse<ResponseUser>> = remoteDataSource.profileUser(username)
    override fun resultDiagnosis(
        age: String,
        gender: String,
        polyuria: String,
        polydipsia: String,
        swl: String,
        weakness: String,
        polyphagia: String,
        gt: String,
        vb: String,
        itching: String,
        irritabiity: String,
        dh: String,
        pp: String,
        ms: String,
        alopecia: String,
        obesity: String
    ): LiveData<ApiResponse<ResponseClassification>> = remoteDataSource.resultDiagnosis(age,gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity)


    override fun getDetailNews(newsId: Int): LiveData<NewsEntity> = localDataSource.getDetailNews(newsId)

    override fun setFavoriteNews(news: NewsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteNews(news)
        }
    }

    override fun getListFavoriteNews(): LiveData<PagedList<NewsEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getLisFavoriteNews(), config).build()
    }

    override fun getUserByUsername(username: String): LiveData<PagedList<UserDiseaseEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getUserByUsername(username), config).build()
    }

    override fun insertResult(user: UserDiseaseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertResult(user)
        }
    }

    override fun deleteResult(user: UserDiseaseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteResult(user)
        }
    }
}