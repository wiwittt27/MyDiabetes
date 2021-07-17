package com.alawiyaa.mydiabetes.ui.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import com.alawiyaa.mydiabetes.ui.signup.register.RegisterViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    private lateinit var profileViewModel: ProfileViewModel


    private val dummyName = "Imam Yusril Alawi"
    private val dummyGender = "Male"
    private val dummyUsername = "alawiyaa"
    private val dummyPassword = "12345"
    private val dummyId = "1"


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observer: Observer<ApiResponse<ResponseUser>>


    @Mock
    private lateinit var response: ResponseUser


    @Before
    fun before() {
        profileViewModel = ProfileViewModel(mDiabetesRepository)
    }


    @Test
    fun profileUser() {
        val dummyResponse = ApiResponse.success(response)
//        val result = MutableLiveData<ApiResponse<ResponseStatus>>()
//        result.value = dummyResponse
        Mockito.`when`(dummyResponse.body?.id).thenReturn(dummyId)
        Mockito.`when`(dummyResponse.body?.name).thenReturn(dummyName)
        Mockito.`when`(dummyResponse.body?.gender).thenReturn(dummyGender)
        Mockito.`when`(dummyResponse.body?.username).thenReturn(dummyUsername)
        Mockito.`when`(dummyResponse.body?.password).thenReturn(dummyPassword)
        val result = MutableLiveData<ApiResponse<ResponseUser>>()
        result.value = dummyResponse



        Mockito.`when`(mDiabetesRepository.profileUser(dummyUsername)).thenReturn(result)
        val userProfile = profileViewModel.profileUser(dummyUsername).value?.body

        Mockito.verify(mDiabetesRepository).profileUser(dummyUsername)
        Assert.assertEquals("1", userProfile?.id)
        Assert.assertEquals("Imam Yusril Alawi", userProfile?.name)
        Assert.assertEquals("Male", userProfile?.gender)
        Assert.assertEquals("alawiyaa", userProfile?.username)
        Assert.assertEquals("12345", userProfile?.password)

        profileViewModel.profileUser(dummyUsername).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyResponse)

    }
}