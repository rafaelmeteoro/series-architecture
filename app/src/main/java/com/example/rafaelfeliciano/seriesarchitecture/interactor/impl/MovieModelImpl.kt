package com.example.rafaelfeliciano.seriesarchitecture.interactor.impl

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.model.cache.MovieCache
import com.example.rafaelfeliciano.seriesarchitecture.net.services.MovieServices
import com.example.rafaelfeliciano.seriesarchitecture.util.extensions.asObservable
import io.reactivex.Observable
import java.io.IOException
import javax.inject.Inject

class MovieModelImpl @Inject
constructor(
        private val services: MovieServices,
        private val movieCache: MovieCache
) : MovieModel {

    override fun getCatalog(): Observable<Catalog> {
        val fromCache = movieCache.get()
        return Observable.just(fromCache)
                .flatMap { cacheMovie ->
                    if (cacheMovie.isEmpty())
                        services.getMovies().flatMap {
                            Observable.fromIterable(it)
                                    .map { Movie(it) }
                                    .toList()
                                    .toObservable()
                                    .map {
                                        movieCache.put(it)
                                        Catalog(it)
                                    }
                        }.onErrorReturn { throwable ->
                            if (throwable is IOException) Catalog(cacheMovie)
                            else throw throwable
                        }
                    else
                        cacheMovie.asObservable()
                                .map { Catalog(it) }
                }
    }
}