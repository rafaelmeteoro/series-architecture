package com.example.rafaelfeliciano.seriesarchitecture.model.cache.impl

import com.example.rafaelfeliciano.seriesarchitecture.dao.MovieDAO
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.model.cache.MovieCache
import javax.inject.Inject

class MovieCacheImpl @Inject constructor() : MovieCache {

    private val dao = MovieDAO()

    override fun put(movies: List<Movie>) {
        dao.save(movies)
    }

    override fun get(): List<Movie>? {
        return dao.findAll()
    }
}