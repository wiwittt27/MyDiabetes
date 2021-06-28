package com.alawiyaa.mydiabetes.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity


@Database(entities = [UserDiseaseEntity::class], version = 1,exportSchema = false)
abstract class UserDiseaseDatabase :RoomDatabase() {

    abstract fun userDao(): UserDiseaseDao
    companion object {

        @Volatile
        private var INSTANCE: UserDiseaseDatabase? = null

        fun getInstance(context: Context): UserDiseaseDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDiseaseDatabase::class.java,
                    "db_diabetes"
                ).build()
            }
    }
}