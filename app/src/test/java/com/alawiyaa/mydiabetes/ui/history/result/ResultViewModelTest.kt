package com.alawiyaa.mydiabetes.ui.history.result

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import com.alawiyaa.mydiabetes.ui.profile.ProfileViewModel
import junit.framework.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResultViewModelTest {


    private lateinit var resultViewModel: ResultViewModel

    private val dummyGender = "Yes"
    private val dummyPolyuria= "Yes"
    private val dummyPolydipsia= "Yes"
    private val dummySWL= "Yes"
    private val dummyWeakness= "Yes"
    private val dummyPolyphagia= "Yes"
    private val dummyGT= "Yes"
    private val dummyVB= "Yes"
    private val dummyItching= "Yes"
    private val dummyIrritabiity= "Yes"
    private val dummyDH= "Yes"
    private val dummyPP= "Yes"
    private val dummyMS= "Yes"
    private val dummyAlopecia= "Yes"
    private val dummyObesity= "Yes"

    private val dummyResult= "Positive"


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observer: Observer<ApiResponse<ResponseClassification>>


    @Mock
    private lateinit var response: ResponseClassification


    @Before
    fun before() {
        resultViewModel = ResultViewModel(mDiabetesRepository)
    }


    @Test
    fun resultClassification() {
        val dummyResponse = ApiResponse.success(response)
//        val result = MutableLiveData<ApiResponse<ResponseStatus>>()
//        result.value = dummyResponse
        Mockito.`when`(dummyResponse.body?.hasil).thenReturn(dummyResult)
        val result = MutableLiveData<ApiResponse<ResponseClassification>>()
        result.value = dummyResponse


        Mockito.`when`(mDiabetesRepository.resultDiagnosis(dummyGender,dummyPolyuria,dummyPolydipsia,dummySWL,dummyWeakness,dummyPolyphagia,dummyGT,dummyVB,dummyItching,dummyIrritabiity,dummyDH,dummyPP,dummyMS,dummyAlopecia,dummyObesity)).thenReturn(result)
        val userClassification = resultViewModel.resultClassification(dummyGender,dummyPolyuria,dummyPolydipsia,dummySWL,dummyWeakness,dummyPolyphagia,dummyGT,dummyVB,dummyItching,dummyIrritabiity,dummyDH,dummyPP,dummyMS,dummyAlopecia,dummyObesity).value?.body

        Mockito.verify(mDiabetesRepository).resultDiagnosis(dummyGender,dummyPolyuria,dummyPolydipsia,dummySWL,dummyWeakness,dummyPolyphagia,dummyGT,dummyVB,dummyItching,dummyIrritabiity,dummyDH,dummyPP,dummyMS,dummyAlopecia,dummyObesity)
        Assert.assertEquals("Positive", userClassification?.hasil)

        resultViewModel.resultClassification(dummyGender,dummyPolyuria,dummyPolydipsia,dummySWL,dummyWeakness,dummyPolyphagia,dummyGT,dummyVB,dummyItching,dummyIrritabiity,dummyDH,dummyPP,dummyMS,dummyAlopecia,dummyObesity).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyResponse)
    }
}