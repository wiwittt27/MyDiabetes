package com.alawiyaa.mydiabetes.ui.signup.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    private lateinit var registerViewModel: RegisterViewModel


    private val dummyName = "Imam Yusril Alawi"
    private val dummyGender = "Male"
    private val dummyUsername = "alawiyaa"
    private val dummyPassword = "12345"

    private val dummySuccess = "Sukses Register!"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observer: Observer<ApiResponse<ResponseStatus>>


    @Mock
    private lateinit var response: ResponseStatus


    @Before
    fun before() {
        registerViewModel = RegisterViewModel(mDiabetesRepository)
    }

    @Test
    fun registerUser() {
        val dummyResponse = ApiResponse.success(response)
        Mockito.`when`(dummyResponse.body?.status).thenReturn(dummySuccess)
        val result = MutableLiveData<ApiResponse<ResponseStatus>>()
        result.value = dummyResponse


        Mockito.`when`(
            mDiabetesRepository.userRegister(
                dummyName,
                dummyGender,
                dummyUsername,
                dummyPassword
            )
        ).thenReturn(result)
        val userLogin = registerViewModel.registerUser(
            dummyName,
            dummyGender,
            dummyUsername,
            dummyPassword
        ).value?.body

        Mockito.verify(mDiabetesRepository).userRegister(dummyName,dummyGender ,dummyUsername, dummyPassword)
        Assert.assertEquals(dummySuccess, userLogin?.status)

        registerViewModel.registerUser(dummyName,dummyGender ,dummyUsername, dummyPassword).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyResponse)

    }
}