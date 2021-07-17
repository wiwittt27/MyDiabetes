package com.alawiyaa.mydiabetes.ui.history.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification

class ResultViewModel(private val mDiabetesRepository: DiabetesRepository) : ViewModel(){

    fun resultClassification(gender: String, polyuria:String, polydipsia:String,swl:String,weakness:String,polyphagia:String, gt:String,vb:String,itching:String,irritabiity:String,dh:String,pp:String,ms:String,alopecia:String,obesity:String) : LiveData<ApiResponse<ResponseClassification>> = mDiabetesRepository.resultDiagnosis(gender,polyuria,polydipsia,swl,weakness,polyphagia,gt,vb,itching,irritabiity,dh,pp,ms,alopecia,obesity)




    fun insertResult(user: UserDiseaseEntity) {
        mDiabetesRepository.insertResult(user)
    }

    fun deleteResult(user: UserDiseaseEntity) {
        mDiabetesRepository.deleteResult(user)
    }


}