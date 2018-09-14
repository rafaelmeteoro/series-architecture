package com.example.rafaelfeliciano.seriesarchitecture.interactor

import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import io.reactivex.Observable

interface MovieModel {
    fun getMovie(): Observable<List<Movie>>
}