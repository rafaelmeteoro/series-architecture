package com.example.rafaelfeliciano.seriesarchitecture.config

import com.example.rafaelfeliciano.seriesarchitecture.BuildConfig
import javax.inject.Inject

class AppConfigImpl @Inject constructor() : AppConfig {

    override val apiBaseUrl: String = BuildConfig.API_BASE_URL

    override val apiKey: String = BuildConfig.API_KEY

    override val apiVersion: Int = 2
}