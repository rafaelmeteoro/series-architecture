package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.support.v7.widget.CardView
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.InjectableIFragment

abstract class HorMovieFragment : InjectableIFragment() {
    abstract fun getCardView(): CardView?
    abstract fun updateItem(movie: Movie)
    abstract fun getItem(): Movie?
}