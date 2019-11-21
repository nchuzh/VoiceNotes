package com.example.voicenotes.di.component

import android.content.Context
import com.example.voicenotes.data.repository.PastebinRepository
import com.example.voicenotes.di.qualifier.AppContext

interface SingletonComponent {

    @AppContext
    fun provideContext(): Context

    fun providePastebinRepository(): PastebinRepository
}