package com.alawiyaa.mydiabetes.ui.signup.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.remote.ApiResponse
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel

    private val dummyUsername = "alawiyaa"
    private val dummyPassword = "12345"

    private val dummySuccess = "Sukses Login!"

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
        loginViewModel = LoginViewModel(mDiabetesRepository)
    }

    @Test
    fun userLogin() {
        val dummyResponse = ApiResponse.success(response)

        `when`(dummyResponse.body?.status).thenReturn(dummySuccess)
        val result = MutableLiveData<ApiResponse<ResponseStatus>>()
        result.value = dummyResponse


        `when`(mDiabetesRepository.userLogin(dummyUsername, dummyPassword)).thenReturn(result)
        val userLogin = loginViewModel.userLogin(dummyUsername, dummyPassword).value?.body

        verify(mDiabetesRepository).userLogin(dummyUsername, dummyPassword)
        assertEquals("Sukses Login!", userLogin?.status)

        loginViewModel.userLogin(dummyUsername, dummyPassword).observeForever(observer)
        verify(observer).onChanged(dummyResponse)

    }
}