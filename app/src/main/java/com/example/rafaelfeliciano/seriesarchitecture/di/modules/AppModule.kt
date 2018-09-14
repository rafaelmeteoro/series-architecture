package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import android.app.Application
import android.content.Context
import com.example.rafaelfeliciano.seriesarchitecture.App
import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfig
import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfigImpl
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

    @Provides
    @Singleton
    fun provideAppConfig(appConfig: AppConfigImpl): AppConfig = appConfig
}