package com.alawiyaa.mydiabetes.ui.history

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HistoryViewModelTest {
    private lateinit var viewModel: HistoryViewModel
    private val dummyUsername = "alawiyaa"


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observerNews: Observer<PagedList<UserDiseaseEntity>>

    @Mock
    private lateinit var newsPagedList: PagedList<UserDiseaseEntity>

    @Before
    fun setUp() {
        viewModel = HistoryViewModel(mDiabetesRepository)
    }


    @Test
    fun getAllResultUsername() {

        val dummyNews = newsPagedList
        Mockito.`when`(dummyNews.size).thenReturn(5)
        val news = MutableLiveData<PagedList<UserDiseaseEntity>>()
        news.value = dummyNews

        Mockito.`when`(mDiabetesRepository.getUserByUsername(dummyUsername)).thenReturn(news)
        val newsEntity = viewModel.getAllResultUsername(dummyUsername).value
        Mockito.verify(mDiabetesRepository).getUserByUsername(dummyUsername)
        assertNotNull(mDiabetesRepository)
        assertEquals(5, newsEntity?.size)

        viewModel.getAllResultUsername(dummyUsername).observeForever(observerNews)
        Mockito.verify(observerNews).onChanged(dummyNews)
    }
}