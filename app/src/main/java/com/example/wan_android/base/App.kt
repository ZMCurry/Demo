package com.example.wan_android.base

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

class App : Application() {
    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        Stetho.initializeWithDefaults(this);
    }
}