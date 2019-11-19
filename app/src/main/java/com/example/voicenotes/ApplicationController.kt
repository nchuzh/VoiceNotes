package com.example.voicenotes

import android.app.Application
import com.example.voicenotes.di.module.AppModule
import com.example.voicenotes.di.module.component.ApplicationComponent
import com.example.voicenotes.di.module.component.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject


class ApplicationController: Application() {
    @Inject
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}