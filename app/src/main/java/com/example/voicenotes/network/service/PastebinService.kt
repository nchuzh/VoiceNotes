package com.example.voicenotes.network.service

import com.example.voicenotes.network.PATH_LOGIN
import com.example.voicenotes.network.request.LoginRequest
import com.example.voicenotes.network.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface PastebinService {
    @POST(PATH_LOGIN)
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}