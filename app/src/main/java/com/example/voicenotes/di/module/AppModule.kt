package com.example.voicenotes.di.module

import android.app.Application
import android.content.Context
import com.example.voicenotes.di.module.qualifier.AppContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    @AppContext
    fun provideContext(): Context = application
}