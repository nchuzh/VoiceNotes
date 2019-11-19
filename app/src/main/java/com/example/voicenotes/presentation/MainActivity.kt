package com.example.voicenotes.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.example.voicenotes.ApplicationController
import com.example.voicenotes.R
import com.example.voicenotes.di.module.ActivitiesModule
import com.example.voicenotes.di.module.component.DaggerActivityComponent
import com.example.voicenotes.domain.LoginToPastebinUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var listening = false

    @Inject
    lateinit var loginUseCase: LoginToPastebinUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerActivityComponent.builder()
            .applicationComponent((applicationContext as ApplicationController).applicationComponent)
            .activitiesModule(ActivitiesModule(this))
            .build()
            .inject(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        view_indicator.visibility = INVISIBLE


        fab.setOnClickListener { view ->
            loginUseCase.execute("", "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Timber.d("Success")
                    }, {
                        Timber.d(it)
                    })
            if (!listening) {
                listening = true
                view_indicator.startAnimation()
                view_indicator.visibility = VISIBLE

            } else {
                listening = false
                view_indicator.visibility = INVISIBLE
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
