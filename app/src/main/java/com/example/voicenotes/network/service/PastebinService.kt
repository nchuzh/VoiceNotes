package com.example.voicenotes.network.service

import com.example.voicenotes.network.response.GetDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PastebinService {
    @GET("url")
    fun getData(@Path("id") id: String): Call<GetDataResponse>
}