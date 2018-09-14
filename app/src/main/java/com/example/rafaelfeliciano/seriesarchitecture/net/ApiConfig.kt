package com.example.rafaelfeliciano.seriesarchitecture.net

import com.example.rafaelfeliciano.seriesarchitecture.config.AppConfig
import javax.inject.Inject

class ApiConfig @Inject constructor(private val appConfig: AppConfig) {

    val baseUrl: String
        get() = appConfig.apiBaseUrl

    val apiKey: String
        get() = appConfig.apiKey

    val apiVersion: Int
        get() = appConfig.apiVersion
}