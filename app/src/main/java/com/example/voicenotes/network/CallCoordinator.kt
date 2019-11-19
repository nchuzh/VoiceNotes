package com.example.voicenotes.network

import android.util.MalformedJsonException
import retrofit2.Call
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLHandshakeException

@Singleton
class CallCoordinator @Inject constructor() {

    @Throws(
        ConnectException::class, SocketTimeoutException::class,
        UnknownHostException::class, SSLHandshakeException::class, SocketException::class,
        MalformedJsonException::class
    )
    fun <T> execute(call: Call<T>): T? {
        try {
            val response = call.execute()

            if (response.isSuccessful) {
                return response.body()
            } else {
                throw Exception() //TODO
            }
        } catch (exception: Exception) {
            Timber.e(exception)
            throw exception
        }
    }
}