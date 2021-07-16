package com.alawiyaa.mydiabetes.data.source.remote.network

import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseClassification
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseNewsItem
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseStatus
import com.alawiyaa.mydiabetes.data.source.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("diabetes/api/login.php")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseStatus>

    @FormUrlEncoded
    @POST("diabetes/api/register.php")
    fun registerUser(
        @Field("name") name: String,
        @Field("gender") gender: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("level") level: String = "User"
    ): Call<ResponseStatus>



    @FormUrlEncoded
    @POST("diabetes/api/user_clasificatioin.php")
    fun userClassification(
        @Field("gender") gender: String,
        @Field("polyuria") polyuria: String,
        @Field("polydipsia") polydipsia: String,
        @Field("sudden_weight_loss") swl: String,
        @Field("weakness") weakness: String,
        @Field("polyphagia") polyphagia: String,
        @Field("genital_thrush") genitalThrush: String,
        @Field("visual_blurring") visualBlurring: String,
        @Field("itching") itching: String,
        @Field("irritability") irritability: String,
        @Field("delayed_healing") delayedHealing: String,
        @Field("partial_paresis") partialParesis: String,
        @Field("muscle_stiffness") muscleStiffness: String,
        @Field("alopecia") alopecia: String,
        @Field("obesity") obesity: String,

    ): Call<ResponseClassification>

    @GET("diabetes/api/user_profile.php")
    fun getUserProfile(@Query("username") username: String): Call<ResponseUser>

    @GET("diabetes/api/news_diabetes.php")
    fun getNewsDiabetes(@Query("type") type: String): Call<ArrayList<ResponseNewsItem>>
}