package com.example.rafaelfeliciano.seriesarchitecture.ui.splash

import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IPresenter
import io.reactivex.Observable

interface SplashContract {
    interface View {
        fun navigateToMovies()
    }

    interface Presenter : IPresenter<View> {
        fun getCatalog(): Observable<Catalog>
    }
}