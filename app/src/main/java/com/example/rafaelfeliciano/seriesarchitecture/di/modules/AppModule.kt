package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import android.app.Application
import android.content.Context
import com.example.rafaelfeliciano.seriesarchitecture.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApp() = application as App

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context =
            application.applicationContext
}