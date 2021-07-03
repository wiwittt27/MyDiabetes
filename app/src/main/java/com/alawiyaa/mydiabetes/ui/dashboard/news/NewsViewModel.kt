package com.alawiyaa.mydiabetes.ui.dashboard.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.vo.Resource

class NewsViewModel(private val mDiabetesRepository: DiabetesRepository) :ViewModel() {


    fun getTypeDiabetes(type :String) :   LiveData<Resource<PagedList<NewsEntity>>> = mDiabetesRepository.getListNews(type)
    fun getTypeDrug(type :String) :   LiveData<Resource<PagedList<NewsEntity>>> = mDiabetesRepository.getListNews(type)
    fun getTypeFactorGeneral(type :String) :   LiveData<Resource<PagedList<NewsEntity>>> = mDiabetesRepository.getListNews(type)
    fun getTypeFactorRisk(type :String) :   LiveData<Resource<PagedList<NewsEntity>>> = mDiabetesRepository.getListNews(type)

}