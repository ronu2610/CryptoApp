package com.ronak.junoapplication;

import android.app.Application
import com.facebook.stetho.Stetho

open class JunoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}

