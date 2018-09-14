package com.example.rafaelfeliciano.seriesarchitecture.net.services

import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieServices {

    @GET("/shows/popular?page=1&limit=30&extended=images,full")
    fun getMovies(): Observable<List<Movie>>
}