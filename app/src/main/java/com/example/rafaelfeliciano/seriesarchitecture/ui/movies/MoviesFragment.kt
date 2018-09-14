package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.PresenterIFragment
import javax.inject.Inject

class MoviesFragment : PresenterIFragment<MoviesContract.Presenter>(), MoviesContract.View {

    @Inject
    internal lateinit var presenter: MoviesContract.Presenter

    companion object {

        @JvmStatic
        fun newInstance() = MoviesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun getPresenter(): MoviesContract.Presenter {
        return presenter
    }
}