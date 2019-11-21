package com.example.voicenotes

import android.app.Application
import com.example.voicenotes.di.component.ApplicationComponent
import com.example.voicenotes.di.component.DaggerApplicationComponent
import com.example.voicenotes.di.module.AppModule
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.config.TinkConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import timber.log.Timber
import java.io.IOException
import java.security.GeneralSecurityException
import javax.inject.Inject


class ApplicationController: Application() {
    @Inject
    lateinit var applicationComponent: ApplicationComponent
    private val PREF_FILE_NAME = "hello_world_pref"
    private val TINK_KEYSET_NAME = "hello_world_keyset"
    private val MASTER_KEY_URI = "android-keystore://hello_world_master_key"
    private lateinit var aead: Aead

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build().inject(this)

        if (BuildConfig.IS_LOGGING_ENABLED) {
            Timber.plant(Timber.DebugTree())
        }

        try {
            TinkConfig.register()
            aead = getOrGenerateNewKeysetHandle().getPrimitive(Aead::class.java)
        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun getOrGenerateNewKeysetHandle(): KeysetHandle {
        return AndroidKeysetManager.Builder()
            .withSharedPref(applicationContext, TINK_KEYSET_NAME, PREF_FILE_NAME)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
    }
}