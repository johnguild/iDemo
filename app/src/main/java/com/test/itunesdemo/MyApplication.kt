package com.test.itunesdemo

import android.app.Application
import com.facebook.stetho.Stetho

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        // stetho for debugging local database on browser
        Stetho.initializeWithDefaults(this)
    }
}
