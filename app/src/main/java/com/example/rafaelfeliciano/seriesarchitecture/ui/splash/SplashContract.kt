package com.example.rafaelfeliciano.seriesarchitecture.ui.splash

import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IPresenter
import io.reactivex.Observable

interface SplashContract {
    interface View
    interface Presenter : IPresenter<View> {
        fun getMovies(): Observable<List<Movie>>
    }
}