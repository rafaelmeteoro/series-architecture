package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesFragment
import com.example.rafaelfeliciano.seriesarchitecture.ui.widgets.dialog.AppDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeAppDialog(): AppDialog

    @ActivityScope
    @ContributesAndroidInjector(modules = [(MoviesModule::class)])
    abstract fun contributeMoviesFragment(): MoviesFragment
}