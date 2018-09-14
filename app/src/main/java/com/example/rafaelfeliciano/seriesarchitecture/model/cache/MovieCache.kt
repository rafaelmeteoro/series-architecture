package com.example.rafaelfeliciano.seriesarchitecture.model.cache

import com.example.rafaelfeliciano.seriesarchitecture.model.Movie

interface MovieCache {
    fun put(movies: List<Movie>)
    fun get(): List<Movie>?
}