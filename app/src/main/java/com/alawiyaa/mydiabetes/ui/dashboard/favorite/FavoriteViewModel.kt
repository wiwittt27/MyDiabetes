package com.alawiyaa.mydiabetes.ui.dashboard.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity

class FavoriteViewModel constructor(private val mDiabetesRepository: DiabetesRepository) : ViewModel() {
    fun getListFavoriteNews(): LiveData<PagedList<NewsEntity>> = mDiabetesRepository.getListFavoriteNews()

    fun setFavoriteTvShow(news: NewsEntity){
        mDiabetesRepository.setFavoriteNews (news)
    }

}