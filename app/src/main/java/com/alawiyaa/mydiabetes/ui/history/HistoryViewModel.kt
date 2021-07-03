package com.alawiyaa.mydiabetes.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity

class HistoryViewModel (private val mDiabetesRepository: DiabetesRepository) :ViewModel() {
    fun getAllResultUsername(username : String) : LiveData<PagedList<UserDiseaseEntity>>  {
       return mDiabetesRepository.getUserByUsername(username)
    }
}