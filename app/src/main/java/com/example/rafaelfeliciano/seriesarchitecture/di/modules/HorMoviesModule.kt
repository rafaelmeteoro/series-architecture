package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.HorMoviesContract
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.HorMoviesPresenter
import dagger.Module
import dagger.Provides

@Module
class HorMoviesModule {

    @Provides
    @ActivityScope
    fun provideHorMoviesPresenter(presenter: HorMoviesPresenter): HorMoviesContract.Presenter = presenter
}