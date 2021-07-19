package com.alawiyaa.mydiabetes.ui.dashboard.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
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

class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observerNews: Observer<PagedList<NewsEntity>>

    @Mock
    private lateinit var newsPagedList: PagedList<NewsEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(mDiabetesRepository)
    }



    @Test
    fun getListFavoriteMovie() {
        val dummyNews = newsPagedList
        Mockito.`when`(dummyNews.size).thenReturn(5)
        val news = MutableLiveData<PagedList<NewsEntity>>()
        news.value = dummyNews

        Mockito.`when`(mDiabetesRepository.getListFavoriteNews()).thenReturn(news)
        val newsEntity = viewModel.getListFavoriteNews().value
        Mockito.verify(mDiabetesRepository).getListFavoriteNews()
        assertNotNull(mDiabetesRepository)
        assertEquals(5, newsEntity?.size)

        viewModel.getListFavoriteNews().observeForever(observerNews)
        Mockito.verify(observerNews).onChanged(dummyNews)
    }


}