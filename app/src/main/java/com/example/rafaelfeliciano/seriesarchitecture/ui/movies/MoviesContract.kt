package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IPresenter
import io.reactivex.Flowable

interface MoviesContract {
    interface View {
        fun addItems(items: List<Movie>)
        fun onMoviesError()
    }

    interface Presenter : IPresenter<View> {
        fun getMovies(): Flowable<Catalog>
    }
}