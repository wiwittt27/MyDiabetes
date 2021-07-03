package com.alawiyaa.mydiabetes.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.local.room.UserDiseaseDao

class LocalDataSource constructor(private val mUserDao: UserDiseaseDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mUserDao: UserDiseaseDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mUserDao)
    }

    fun getListNews(type:String): DataSource.Factory<Int, NewsEntity> = mUserDao.getListNews(type)

    fun getLisFavoriteNews(): DataSource.Factory<Int, NewsEntity> = mUserDao.getListFavoriteNews()

    fun insertNews(news: List<NewsEntity>) = mUserDao.insertNews(news)

    fun getDetailNews(movieId: Int) : LiveData<NewsEntity> = mUserDao.getDetailTvShowById(movieId)

    fun setFavoriteNews(news : NewsEntity) {
        news.isFavorite =!news.isFavorite
        mUserDao.updateNews(news)
    }

    fun getUserByUsername(username : String) : DataSource.Factory<Int, UserDiseaseEntity> = mUserDao.getListBookmarkUser(username)

    fun insertResult(UserDiseaseEntity: UserDiseaseEntity) = mUserDao.insert(UserDiseaseEntity)

    fun deleteResult(UserDiseaseEntity: UserDiseaseEntity) = mUserDao.delete(UserDiseaseEntity)

}