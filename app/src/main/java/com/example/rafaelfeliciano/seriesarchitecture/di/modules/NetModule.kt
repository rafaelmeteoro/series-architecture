package com.example.rafaelfeliciano.seriesarchitecture.di.modules

import com.example.rafaelfeliciano.seriesarchitecture.App
import com.example.rafaelfeliciano.seriesarchitecture.net.ApiConfig
import com.example.rafaelfeliciano.seriesarchitecture.net.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetModule {

    @Provides
    @Reusable
    fun provideHttpCache(application: App): Cache =
            (10 * 1024 * 1024)
                    .let { Cache(application.cacheDir, it.toLong()) }

    @Provides
    @Reusable
    fun provideGson(): Gson =
            GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .disableHtmlEscaping()
                    .create()

    @Provides
    @IntoSet
    @Reusable
    fun provideAuthenticateInterceptor(interceptor: AuthInterceptor): Interceptor = interceptor

    @Provides
    @ElementsIntoSet
    @Reusable
    fun provideLogInterceptor(): Set<Interceptor> =
            setOf(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

    @Provides
    @Reusable
    fun provideOkHttpClientBuilder(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
        interceptors.forEach { builder.addInterceptor(it) }
        return builder
    }

    @Provides
    @Reusable
    fun provideOkhttpClient(okHttpClient: OkHttpClient.Builder, cache: Cache): OkHttpClient =
            okHttpClient.cache(cache)
                    .build()

    @Provides
    @Reusable
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, apiConfig: ApiConfig): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(apiConfig.baseUrl)
                    .client(okHttpClient)
                    .build()
}