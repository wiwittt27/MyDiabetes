package com.alawiyaa.mydiabetes.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alawiyaa.mydiabetes.data.source.local.entitiy.NewsEntity
import com.alawiyaa.mydiabetes.data.source.local.entitiy.UserDiseaseEntity

@Dao
interface UserDiseaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE,entity = UserDiseaseEntity::class)
    fun insertAll(user: List<UserDiseaseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: UserDiseaseEntity)



    @Query("SELECT * FROM user_disease WHERE user_name =:username ORDER BY id ='DESC'")
    fun getListBookmarkUser(username : String) : DataSource.Factory<Int, UserDiseaseEntity>



    @Delete
    fun delete(user: UserDiseaseEntity)

    //News

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = NewsEntity::class)
    fun insertNews(tvShows: List<NewsEntity>)

    @Query("SELECT * FROM tb_news WHERE type =:type")
    fun getListNews(type : String) : DataSource.Factory<Int, NewsEntity>


    @Query("SELECT * FROM tb_news WHERE is_favorite =1 ORDER BY id DESC")
    fun getListFavoriteNews() : DataSource.Factory<Int, NewsEntity>

    @Query("SELECT * FROM tb_news WHERE news_id = :newsId")
    fun getDetailTvShowById(newsId: Int) : LiveData<NewsEntity>

    @Update(entity = NewsEntity::class)
    fun updateNews(movie : NewsEntity)






}