package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.di.ActivityScope
import com.example.rafaelfeliciano.seriesarchitecture.ui.splash.SplashContract
import com.example.rafaelfeliciano.seriesarchitecture.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun provideSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter = presenter
}