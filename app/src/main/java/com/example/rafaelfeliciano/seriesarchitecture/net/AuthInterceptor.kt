package com.example.rafaelfeliciano.seriesarchitecture.net

import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val appConfig: AppConfig) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("trakt-api-version", appConfig.apiVersion.toString())
                .header("trakt-api-key", appConfig.apiKey)
        return chain.proceed(requestBuilder.build())
    }
}