package com.shrouk.movieapp.Api

import com.shrouk.movieapp.movieModel.Data
import com.shrouk.movieapp.movieModel.LoginResponse
import com.shrouk.movieapp.movieModel.Movies
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("products")
    fun getData(): Call<Movies>

    @GET("products")
    fun getId(
        @Query("id") id:Int
    ):Call<Data>


    @FormUrlEncoded
    @POST("login")
    fun userLogin(

        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>


}