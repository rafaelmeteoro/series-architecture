package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.interactor.impl.MovieModelImpl
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesContract
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.MoviesPresenter
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class MoviesModule {

    @Provides
    @ActivityScope
    fun provideMoviesPresenter(presenter: MoviesPresenter): MoviesContract.Presenter = presenter

    @Provides
    @Reusable
    fun provideMovieModel(model: MovieModelImpl): MovieModel = model
}