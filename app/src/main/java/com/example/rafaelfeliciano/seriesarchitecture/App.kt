package com.example.rafaelfeliciano.seriesarchitecture

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        Timber.plant(Timber.DebugTree())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}