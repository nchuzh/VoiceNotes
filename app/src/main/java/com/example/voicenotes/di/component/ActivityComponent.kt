package com.example.voicenotes.di.component

import android.content.Context
import com.example.voicenotes.di.module.ActivitiesModule
import com.example.voicenotes.di.qualifier.ActivityContext
import com.example.voicenotes.di.scope.ActivityScope
import com.example.voicenotes.presentation.base.BaseActivity
import com.example.voicenotes.presentation.main.MainScreenActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivitiesModule::class])
interface ActivityComponent : SingletonComponent {

    @ActivityContext
    fun provideActivityContext(): Context

    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainScreenActivity)
}