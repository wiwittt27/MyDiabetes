package com.alawiyaa.mydiabetes.data.source.local.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_news")
@Parcelize
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int?,

    @ColumnInfo(name ="news_id")
    var newsId :String,

    @ColumnInfo(name ="title")
    var title :String,

    @ColumnInfo(name ="description")
    var description :String,

    @ColumnInfo(name ="image_path")
    var imagePath : String,

    @ColumnInfo(name ="source")
    var source :String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
):Parcelable