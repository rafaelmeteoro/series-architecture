package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IPresenter
import io.reactivex.Flowable

interface HorMoviesContract {
    interface View {
        fun setItems(items: List<Movie>)
        fun setPageCount(count: Int, selected: Int)
        fun onMoviesError()
    }

    interface Presenter : IPresenter<View> {
        fun getMovies(): Flowable<Catalog>
    }
}