package com.example.voicenotes.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = getRequest(request)

        return chain.proceed(newRequest)
    }

    fun getRequest(request: Request): Request {
        val newRequestBuilder = request.newBuilder()

        with (newRequestBuilder) {
            addHeader("api_dev_key", API_DEV_KEY)
        }

        return newRequestBuilder.build()
    }
}