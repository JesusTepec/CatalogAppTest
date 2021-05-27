package com.catalogapptest

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs
import timber.log.Timber
import timber.log.Timber.DebugTree

class CatalogApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
        initTimber()
        initPrefs()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }

    private fun initPrefs() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    companion object {
        private var instance: CatalogApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}