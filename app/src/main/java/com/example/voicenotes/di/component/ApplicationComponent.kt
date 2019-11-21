package com.example.voicenotes.di.component

import com.example.voicenotes.ApplicationController
import com.example.voicenotes.di.module.AppModule
import com.example.voicenotes.di.module.NetworkModule
import com.example.voicenotes.di.module.PastebinModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PastebinModule::class])
interface ApplicationComponent : SingletonComponent {

    fun inject(application: ApplicationController)
}