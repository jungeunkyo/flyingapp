package com.example.flying.data

import com.example.flying.model.BaseResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @POST("signup.php")
    @FormUrlEncoded
    fun signUp(
        @Field("id") id: String,
        @Field("pw") password: String,
        @Field("name") name: String,
        @Field("phonenum") phoneNumber: String,
        @Field("email") email: String,
        @Field("membertype") type: String
    ): Call<BaseResponse>

    @POST("login.php")
    @FormUrlEncoded
    fun login(
        @Field("edit_id") edit_id: String,
        @Field("edit_pw") edit_pw: String
    ): Call<BaseResponse>

    @POST("membertype.php")
    @FormUrlEncoded
    fun membertype(
        @Field("edit_id") edit_id: String,
        @Field("edit_pw") edit_pw: String
    ): Call<BaseResponse>

    @POST("update.php")
    @FormUrlEncoded
    fun Update(
        @Field("id") id: String,
        @Field("cafe_name") cafeName: String,
        @Field("city") city: String,
        @Field("gu") gu: String,
        @Field("address") address: String,
        @Field("business_number") businessNumber: String,
        @Field("phone_number") phoneNumber: String,
        @Field("tag1") tag1: String,
        @Field("tag2") tag2: String,
        @Field("tag3") tag3: String
    ): Call<BaseResponse>

    companion object {
        val service: AuthService = Retrofit.Builder().baseUrl("http://192.168.233.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }



}