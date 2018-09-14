package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import android.app.Application
import android.content.Context
import com.example.rafaelfeliciano.seriesarchitecture.App
import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfig
import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfigImpl
import com.example.rafaelfeliciano.seriesarchitecture.dao.realm.Migration
import com.example.rafaelfeliciano.seriesarchitecture.model.cache.MovieCache
import com.example.rafaelfeliciano.seriesarchitecture.model.cache.impl.MovieCacheImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.realm.Realm
import io.realm.RealmConfiguration
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

    @Provides
    fun provideRealmConfig(context: Context): RealmConfiguration {
        Realm.init(context)
        return RealmConfiguration.Builder()
                .schemaVersion(Migration.SCHEMA_VERSION)
                .migration(Migration())
                .build()
    }

    @Provides
    @Reusable
    fun provideMovieCache(cache: MovieCacheImpl): MovieCache = cache
}