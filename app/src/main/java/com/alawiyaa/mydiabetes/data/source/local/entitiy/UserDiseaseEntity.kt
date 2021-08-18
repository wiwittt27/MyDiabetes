package com.alawiyaa.mydiabetes.data.source.local.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_disease")
@Parcelize
data class UserDiseaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "user_name")
    var userName: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "age")
    var age: String? = null,

    @ColumnInfo(name = "gender")
    var gender: String? = null,


    @ColumnInfo(name = "polyuria")
    var polyuria: String? = null,

    @ColumnInfo(name = "polydipsia")
    var polydipsia: String? = null,

    @ColumnInfo(name = "sudden_weight_loss")
    var swl: String? = null,

    @ColumnInfo(name = "weakness")
    var weakness: String? = null,

    @ColumnInfo(name = "polyphagia")
    var polyphagia: String? = null,

    @ColumnInfo(name = "genital_thrush")
    var genitalThrush: String? = null,

    @ColumnInfo(name = "visual_blurring")
    var visualBlurring: String? = null,

    @ColumnInfo(name = "itching")
    var itching: String? = null,

    @ColumnInfo(name = "irritability")
    var irritability: String?  = null,

    @ColumnInfo(name = "delayed_healing")
    var delayedHealing: String? = null,

    @ColumnInfo(name = "partial_paresis")
    var partialParesis: String? = null,

    @ColumnInfo(name = "muscle_stiffness")
    var muscleStiffness: String? = null,

    @ColumnInfo(name = "alopecia")
    var alopecia: String? = null,

    @ColumnInfo(name = "obesity")
    var obesity: String? = null,

    @ColumnInfo(name = "class")
    var classPrediction : String?  = null,

    @ColumnInfo(name = "class_info")
    var classInformation : String?  = null

) : Parcelable