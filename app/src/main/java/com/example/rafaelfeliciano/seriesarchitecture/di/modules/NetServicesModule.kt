package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.net.services.MovieServices
import com.example.rafaelfeliciano.seriesarchitecture.util.extensions.create
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module(includes = [(NetModule::class)])
class NetServicesModule {

    @Provides
    @Reusable
    fun provideMovieServices(retrofit: Retrofit): MovieServices = retrofit.create()
}