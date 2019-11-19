package com.example.voicenotes.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = getBaseRequest(
            request, null
        )

        return chain.proceed(newRequest)
    }

    fun getBaseRequest(oldRequest: Request, overrideHeaders: Map<String, String>? = null): Request {
        val newRequestBuilder = oldRequest.newBuilder()

        //Add common headers for APIs
        with(newRequestBuilder) {
            addHeader("api_dev_key", API_DEV_KEY)

            overrideHeaders?.let {
                overrideHeaders.entries.forEach {
                    removeHeader(it.key)
                    addHeader(it.key, it.value)
                }
            }
        }

        return newRequestBuilder.build()
    }
}