package com.example.voicenotes

import android.app.Application
import com.example.voicenotes.di.module.component.ApplicationComponent
import timber.log.Timber
import javax.inject.Inject


class ApplicationController: Application() {
    @Inject
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}