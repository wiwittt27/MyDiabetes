package com.alawiyaa.mydiabetes.ui.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.UserRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity

class HistoryViewModel (application: Application) :ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)

//    fun getAllResult(sort : String) : LiveData<PagedList<UserDiseaseEntity>> = LivePagedListBuilder(mUserRepository.getAllUsers(sort),20).build()
    fun getAllResultUsername(username : String) : LiveData<PagedList<UserDiseaseEntity>> = LivePagedListBuilder(mUserRepository.getUserByUsername(username),20).build()
}