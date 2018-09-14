package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesContract
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesPresenter
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {

    @Provides
    @ActivityScope
    fun provideMoviesPresenter(presenter: MoviesPresenter): MoviesContract.Presenter = presenter
}