package com.example.voicenotes.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.voicenotes.ApplicationController
import com.example.voicenotes.di.component.ActivityComponent
import com.example.voicenotes.di.component.DaggerActivityComponent
import com.example.voicenotes.di.module.ActivitiesModule

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        getActivityComponent().inject(this)
    }

    fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .applicationComponent((applicationContext as ApplicationController).applicationComponent)
            .activitiesModule(ActivitiesModule(this))
            .build()
    }
}