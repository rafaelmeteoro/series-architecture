package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IPresenter

interface MoviesContract {
    interface View
    interface Presenter : IPresenter<View>
}