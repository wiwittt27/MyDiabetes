package com.alawiyaa.mydiabetes.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity
@Dao
interface UserDiseaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE,entity = UserDiseaseEntity::class)
    fun insertAll(user: List<UserDiseaseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: UserDiseaseEntity)

//    @RawQuery(observedEntities = [UserDiseaseEntity::class])
//    fun getAllUserDisease(query : SupportSQLiteQuery): DataSource.Factory<Int,UserDiseaseEntity>

    @Query("SELECT * FROM user_disease WHERE user_name =:username ORDER BY id ='DESC'")
    fun getListBookmarkUser(username : String) : DataSource.Factory<Int, UserDiseaseEntity>


    @Update(entity = UserDiseaseEntity::class)
    fun updateUser(user : UserDiseaseEntity)

    @Delete
    fun delete(user: UserDiseaseEntity)


}