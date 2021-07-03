package com.alawiyaa.mydiabetes.di

import android.content.Context
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.data.source.local.LocalDataSource
import com.alawiyaa.mydiabetes.data.source.local.room.UserDiseaseDatabase
import com.alawiyaa.mydiabetes.data.source.remote.RemoteDataSource

object Injection {
    fun provideCatalogRepository(context: Context): DiabetesRepository {
        val database = UserDiseaseDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        return DiabetesRepository.getInstance(remoteDataSource,localDataSource)
    }
}