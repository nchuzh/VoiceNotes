package com.example.voicenotes.domain.repository

import io.reactivex.Single

interface PastebinRepositoryInterface {
    fun getLoginToken(login: String, password: String): Single<String>
    fun saveLoginToken(token: String)
}