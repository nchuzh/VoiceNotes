package com.example.voicenotes.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.voicenotes.di.qualifier.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity
}