package com.catalogapptest

import android.app.Application
import android.content.Context
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
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }

    companion object {
        private var instance: CatalogApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}