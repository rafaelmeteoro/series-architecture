package com.example.rafaelfeliciano.seriesarchitecture.util.extensions

import retrofit2.Retrofit

inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)