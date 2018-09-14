package com.example.rafaelfeliciano.seriesarchitecture.interactor.impl

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.net.services.MovieServices
import io.reactivex.Observable
import javax.inject.Inject

class MovieModelImpl @Inject
constructor(
        private val services: MovieServices
) : MovieModel {

    override fun getMovie(): Observable<List<Movie>> {
        return services.getMovies()
    }
}