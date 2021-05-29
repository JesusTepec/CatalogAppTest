package com.catalogapptest

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs
import timber.log.Timber
import timber.log.Timber.DebugTree

class CatalogApp : Application() {

    // val appComponent = DaggerAppComponent.create()

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
        initTimber()
        initPrefs()
        fillPrefs()
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

    private fun fillPrefs() {
        Prefs.putString(KEY_TOKEN, TOKEN)
        Prefs.putInt(KEY_SKILL_ID, SKILL_ID)
        Prefs.putInt(KEY_BABY_ID, BABY_ID)
    }

    companion object {
        private var instance: CatalogApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}