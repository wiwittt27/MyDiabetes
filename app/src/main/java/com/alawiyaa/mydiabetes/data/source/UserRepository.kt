package com.alawiyaa.mydiabetes.data.source

import android.app.Application
import androidx.paging.DataSource
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
import com.alawiyaa.mydiabetes.data.source.local.room.UserDiseaseDao
import com.alawiyaa.mydiabetes.data.source.local.room.UserDiseaseDatabase
import com.alawiyaa.mydiabetes.data.utils.SortUtils
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository (application: Application)  {

   private val mUserDao : UserDiseaseDao
   private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserDiseaseDatabase.getInstance(application)
        mUserDao = db.userDao()
    }

//    fun getAllUsers(sort : String): DataSource.Factory<Int,UserDiseaseEntity> {
//        val query = SortUtils.getSortedQuery(sort)
//        return mUserDao.getAllUserDisease(query)
//    }

    fun getUserByUsername(username : String) : DataSource.Factory<Int,UserDiseaseEntity> = mUserDao.getListBookmarkUser(username)
    fun insert(UserDiseaseEntity: UserDiseaseEntity) {
        executorService.execute { mUserDao.insert(UserDiseaseEntity) }
    }
    fun delete(UserDiseaseEntity: UserDiseaseEntity) {
        executorService.execute { mUserDao.delete(UserDiseaseEntity) }
    }
    fun update(UserDiseaseEntity: UserDiseaseEntity) {
        executorService.execute { mUserDao.updateUser(UserDiseaseEntity) }
    }


}