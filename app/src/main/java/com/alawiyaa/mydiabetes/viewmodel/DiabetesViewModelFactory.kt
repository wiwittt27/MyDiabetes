package com.alawiyaa.mydiabetes.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alawiyaa.mydiabetes.data.source.DiabetesRepository
import com.alawiyaa.mydiabetes.di.Injection
import com.alawiyaa.mydiabetes.ui.dashboard.favorite.FavoriteViewModel
import com.alawiyaa.mydiabetes.ui.dashboard.news.NewsViewModel
import com.alawiyaa.mydiabetes.ui.history.HistoryViewModel
import com.alawiyaa.mydiabetes.ui.history.result.ResultViewModel
import com.alawiyaa.mydiabetes.ui.profile.ProfileViewModel
import com.alawiyaa.mydiabetes.ui.signup.login.LoginViewModel
import com.alawiyaa.mydiabetes.ui.signup.register.RegisterViewModel

class DiabetesViewModelFactory private constructor(private val mDiabetesRepository: DiabetesRepository) :  ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: DiabetesViewModelFactory? = null

        fun getInstance(context: Context): DiabetesViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DiabetesViewModelFactory(Injection.provideCatalogRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(mDiabetesRepository) as T
            }
            modelClass.isAssignableFrom(ResultViewModel::class.java) -> {
                ResultViewModel(mDiabetesRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(mDiabetesRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(mDiabetesRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(mDiabetesRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(mDiabetesRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mDiabetesRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}