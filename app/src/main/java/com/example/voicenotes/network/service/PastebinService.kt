package com.example.voicenotes.network.service

import com.example.voicenotes.network.PATH_LOGIN
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface PastebinService {

    @FormUrlEncoded
    @POST(PATH_LOGIN)
    fun login(
        @Field("api_dev_key") devKey: String,
        @Field("api_user_name") userName: String,
        @Field("api_user_password") password: String
    ): Call<String>
}