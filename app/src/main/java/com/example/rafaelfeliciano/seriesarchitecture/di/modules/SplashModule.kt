package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.interactor.impl.MovieModelImpl
import com.example.rafaelfeliciano.seriesarchitecture.ui.splash.SplashContract
import com.example.rafaelfeliciano.seriesarchitecture.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun provideSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter = presenter

    @Provides
    @Reusable
    fun provideMovieModel(model: MovieModelImpl): MovieModel = model
}