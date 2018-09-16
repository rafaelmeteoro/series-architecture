package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import kotlinx.android.synthetic.main.fragment_card_movie.*

class CardMovieFragment : HorMovieFragment() {

    companion object {
        private const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun getInstance(movie: Movie) =
                CardMovieFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(EXTRA_MOVIE, movie)
                    }
                }
    }

    lateinit var movie: Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_movie, container, false)
    }

    override fun getCardView(): CardView? = cv_movie

    override fun setUpView(view: View?) {
        super.setUpView(view)
        movie = getItem()!!
        refreshViews()
    }

    override fun updateItem(movie: Movie) {
        this.movie = movie
        arguments?.putParcelable(EXTRA_MOVIE, movie)
        refreshViews()
    }

    override fun getItem(): Movie? = arguments?.getParcelable(EXTRA_MOVIE)

    private fun refreshViews() {
        if (!isAdded) return

        tv_card_year.text = getString(R.string.movie_year_format, movie.year)
        tv_card_title.text = movie.title
        tv_card_overview.text = movie.overview
    }
}