package com.alawiyaa.mydiabetes.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser

class ProfileViewModel(private val mDiabetesRepository: DiabetesRepository) : ViewModel() {


    fun profileUser(username: String) : LiveData<ApiResponse<ResponseUser>> = mDiabetesRepository.profileUser(username)

}