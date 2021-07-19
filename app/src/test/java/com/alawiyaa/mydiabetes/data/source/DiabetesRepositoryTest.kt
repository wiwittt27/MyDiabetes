package com.alawiyaa.mydiabetes.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alawiyaa.mydiabetes.data.source.local.LocalDataSource
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.source.remote.RemoteDataSource
import com.alawiyaa.mydiabetes.data.utils.DataDummy
import com.alawiyaa.mydiabetes.ui.LiveDataTestUtil
import com.alawiyaa.mydiabetes.ui.PagedListUtil
import com.alawiyaa.mydiabetes.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DiabetesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val newsRepository = FakeDiabetesRepository(remote,local)

    private val listTypeDiabetes = DataDummy.typeDiabetesResponse()
    private val listTypeDrug = DataDummy.typeNaturalMedicine()
    private val listFactorDiabetes = DataDummy.factorDiabetes()
    private val listFactorRisk = DataDummy.factorRisk()

    private val typeDiabetes = DataDummy.dummyTypeDiabetes()[0]
    private val typeDrug = DataDummy.dummyTypeNaturalMedicine()[0]
    private val factorDiabetes = DataDummy.dummyfactorDiabetes()[0]
    private val factorRisk = DataDummy.dummyfactorRisk()[0]

    private val dummyTypeDiabetes = "diabetes"
    private val dummyNaturalMedicine = "natural_medicine"
    private val dummyFactorDiabetes = "faktor_diabetes"
    private val dummyFactorRiskDiabetes = "faktor_resiko"

    @Test
    fun getListNews() {

        //Type Diabetes
        val dataSourceFactoryTypeDiabetes = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NewsEntity>
        Mockito.`when`(local.getListNews(dummyTypeDiabetes)).thenReturn(dataSourceFactoryTypeDiabetes)
        newsRepository.getListNews(dummyTypeDiabetes)

        val newsEntityTYpeDiabetes = Resource.success(PagedListUtil.mockPagedList(DataDummy.dummyTypeDiabetes()))
        Mockito.verify(local).getListNews(dummyTypeDiabetes)
        assertNotNull(newsEntityTYpeDiabetes.data)
        assertEquals(listTypeDiabetes.size.toLong(), newsEntityTYpeDiabetes.data?.size?.toLong())

        //Type Drug
        val dataSourceFactoryTypeDrug = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NewsEntity>
        Mockito.`when`(local.getListNews(dummyNaturalMedicine)).thenReturn(dataSourceFactoryTypeDrug)
        newsRepository.getListNews(dummyNaturalMedicine)

        val newsEntityTypeDrug = Resource.success(PagedListUtil.mockPagedList(DataDummy.dummyTypeNaturalMedicine()))
        Mockito.verify(local).getListNews(dummyNaturalMedicine)
        assertNotNull(newsEntityTypeDrug.data)
        assertEquals(listTypeDrug.size.toLong(), newsEntityTypeDrug.data?.size?.toLong())

        //Type Factor Diabetes
        val dataSourceFactoryFactorDiabetes = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NewsEntity>
        Mockito.`when`(local.getListNews(dummyFactorDiabetes)).thenReturn(dataSourceFactoryFactorDiabetes)
        newsRepository.getListNews(dummyFactorDiabetes)

        val newsEntityFactorDiabetes = Resource.success(PagedListUtil.mockPagedList(DataDummy.dummyfactorDiabetes()))
        Mockito.verify(local).getListNews(dummyFactorDiabetes)
        assertNotNull(newsEntityFactorDiabetes.data)
        assertEquals(listFactorDiabetes.size.toLong(), newsEntityFactorDiabetes.data?.size?.toLong())

        //Type Factor Risk
        val dataSourceFactoryFactorRisk = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NewsEntity>
        Mockito.`when`(local.getListNews(dummyFactorRiskDiabetes)).thenReturn(dataSourceFactoryFactorRisk)
        newsRepository.getListNews(dummyFactorRiskDiabetes)

        val newsEntityFactorRisk = Resource.success(PagedListUtil.mockPagedList(DataDummy.dummyfactorRisk()))
        Mockito.verify(local).getListNews(dummyFactorRiskDiabetes)
        assertNotNull(newsEntityFactorRisk.data)
        assertEquals(listFactorRisk.size.toLong(), newsEntityFactorRisk.data?.size?.toLong())
    }


    @Test
    fun getDetailNews() {
        //Detail Type Diabetes
        val dummyTypeDiabetes = MutableLiveData<NewsEntity>()
        dummyTypeDiabetes.value = typeDiabetes
        Mockito.`when`(local.getDetailNews(typeDiabetes.newsId.toInt())).thenReturn(dummyTypeDiabetes)

        val dataDummyDiabetes = LiveDataTestUtil.getValue(newsRepository.getDetailNews(typeDiabetes.newsId.toInt()))
        Mockito.verify(local).getDetailNews(typeDiabetes.newsId.toInt())
        assertNotNull(dataDummyDiabetes)
        assertEquals(typeDiabetes.newsId.toInt(), dataDummyDiabetes.id)

        //Detail Type Drug
        val dummyTypeDrug = MutableLiveData<NewsEntity>()
        dummyTypeDrug.value = typeDrug
        Mockito.`when`(local.getDetailNews(typeDrug.newsId.toInt())).thenReturn(dummyTypeDrug)

        val dataDummyDrug = LiveDataTestUtil.getValue(newsRepository.getDetailNews(typeDrug.newsId.toInt()))
        Mockito.verify(local).getDetailNews(typeDrug.newsId.toInt())
        assertNotNull(dataDummyDrug)
        assertEquals(typeDrug.newsId.toInt(), dataDummyDrug.id)

        //Detail Type Factor Diabetes
        val dummyFactorDiabetes = MutableLiveData<NewsEntity>()
        dummyFactorDiabetes.value = factorDiabetes
        Mockito.`when`(local.getDetailNews(factorDiabetes.newsId.toInt())).thenReturn(dummyFactorDiabetes)

        val dataDummyFactorDiabetes = LiveDataTestUtil.getValue(newsRepository.getDetailNews(factorDiabetes.newsId.toInt()))
        Mockito.verify(local).getDetailNews(factorDiabetes.newsId.toInt())
        assertNotNull(dataDummyFactorDiabetes)
        assertEquals(factorDiabetes.newsId.toInt(), dataDummyFactorDiabetes.id)

        //Detail Type Factor Risk
        val dummyFactorRisk = MutableLiveData<NewsEntity>()
        dummyFactorRisk.value = factorRisk
        Mockito.`when`(local.getDetailNews(factorRisk.newsId.toInt())).thenReturn(dummyFactorRisk)

        val dataDummyFactorRisk = LiveDataTestUtil.getValue(newsRepository.getDetailNews(factorRisk.newsId.toInt()))
        Mockito.verify(local).getDetailNews(factorRisk.newsId.toInt())
        assertNotNull(dataDummyFactorRisk)
        assertEquals(factorRisk.newsId.toInt(), dataDummyFactorRisk.id)


    }


    @Test
    fun getListFavoriteNews() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NewsEntity>
        Mockito.`when`(local.getLisFavoriteNews()).thenReturn(dataSourceFactory)
        newsRepository.getListFavoriteNews()

        val newsEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.dummyTypeDiabetes()))
        Mockito.verify(local).getLisFavoriteNews()
        assertNotNull(newsEntity.data)
        assertEquals(listTypeDiabetes.size.toLong(), newsEntity.data?.size?.toLong())
    }



}