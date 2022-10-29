package com.ronak.junoApplication

import android.app.Application
import com.facebook.stetho.Stetho
import com.google.android.material.color.DynamicColors

open class JunoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        Stetho.initializeWithDefaults(this)
    }
}

