package com.example.voicenotes.domain

import io.reactivex.Single

interface PastebinRepositoryInterface {
    fun getData(): Single<String>
}