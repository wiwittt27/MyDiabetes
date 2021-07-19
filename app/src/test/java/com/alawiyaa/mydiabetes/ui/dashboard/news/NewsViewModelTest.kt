package com.alawiyaa.mydiabetes.ui.dashboard.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.vo.Resource
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
class NewsViewModelTest {

    private lateinit var newsViewModel: NewsViewModel

    private val dummyTypeDiabetes = "diabetes"
    private val dummyNaturalMedicine = "natural_medicine"
    private val dummyFactorDiabetes = "faktor_diabetes"
    private val dummyFactorRiskDiabetes = "faktor_resiko"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mDiabetesRepository: DiabetesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<NewsEntity>>>
    @Mock
    private lateinit var newsPagedList: PagedList<NewsEntity>


    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(mDiabetesRepository)
    }



    @Test
    fun getTypeDiabetes() {
        val dummyMovie = Resource.success(newsPagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(4)
        val typeDiabetes = MutableLiveData<Resource<PagedList<NewsEntity>>>()
        typeDiabetes.value = dummyMovie

        Mockito.`when`(mDiabetesRepository.getListNews(dummyTypeDiabetes)).thenReturn(typeDiabetes)
        val newsEntity = newsViewModel.getTypeDiabetes(dummyTypeDiabetes).value?.data
        Mockito.verify(mDiabetesRepository).getListNews(dummyTypeDiabetes)
        assertNotNull(newsEntity)
        assertEquals(4, newsEntity?.size)

        newsViewModel.getTypeDiabetes(dummyTypeDiabetes).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTypeDrug() {
        val dummyMovie = Resource.success(newsPagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(8)
        val typeDiabetes = MutableLiveData<Resource<PagedList<NewsEntity>>>()
        typeDiabetes.value = dummyMovie

        Mockito.`when`(mDiabetesRepository.getListNews(dummyNaturalMedicine)).thenReturn(typeDiabetes)
        val newsEntity = newsViewModel.getTypeDiabetes(dummyNaturalMedicine).value?.data
        Mockito.verify(mDiabetesRepository).getListNews(dummyNaturalMedicine)
        assertNotNull(newsEntity)
        assertEquals(8, newsEntity?.size)

        newsViewModel.getTypeDiabetes(dummyNaturalMedicine).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTypeFactorGeneral() {
        val dummyMovie = Resource.success(newsPagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val typeDiabetes = MutableLiveData<Resource<PagedList<NewsEntity>>>()
        typeDiabetes.value = dummyMovie

        Mockito.`when`(mDiabetesRepository.getListNews(dummyFactorDiabetes)).thenReturn(typeDiabetes)
        val newsEntity = newsViewModel.getTypeDiabetes(dummyFactorDiabetes).value?.data
        Mockito.verify(mDiabetesRepository).getListNews(dummyFactorDiabetes)
        assertNotNull(newsEntity)
        assertEquals(5, newsEntity?.size)

        newsViewModel.getTypeDiabetes(dummyFactorDiabetes).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTypeFactorRisk() {
        val dummyMovie = Resource.success(newsPagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(8)
        val typeDiabetes = MutableLiveData<Resource<PagedList<NewsEntity>>>()
        typeDiabetes.value = dummyMovie

        Mockito.`when`(mDiabetesRepository.getListNews(dummyFactorRiskDiabetes)).thenReturn(typeDiabetes)
        val newsEntity = newsViewModel.getTypeDiabetes(dummyFactorRiskDiabetes).value?.data
        Mockito.verify(mDiabetesRepository).getListNews(dummyFactorRiskDiabetes)
        assertNotNull(newsEntity)
        assertEquals(8, newsEntity?.size)

        newsViewModel.getTypeDiabetes(dummyFactorRiskDiabetes).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}