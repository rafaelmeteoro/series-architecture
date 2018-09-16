package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.HorMoviesActivity
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesActivity
import com.example.rafaelfeliciano.seriesarchitecture.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MoviesModule::class)])
    abstract fun contributeMoviesActivity(): MoviesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(HorMoviesModule::class)])
    abstract fun contributeHorMoviesActivity(): HorMoviesActivity
}