package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.PresenterIFragment
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.adapters.MoviesAdapter
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.decoration.LogoDecoration
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.decoration.MovieSpacesDecoration
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : PresenterIFragment<MoviesContract.Presenter>(), MoviesContract.View {

    @Inject
    internal lateinit var presenter: MoviesContract.Presenter

    private var moviesAdapter: MoviesAdapter? = null
    private var movieItems: MutableList<Movie>? = null
    private var movieSpacesDecoration: MovieSpacesDecoration? = null
    private var logoDecoration: LogoDecoration? = null

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

    override fun setUpView(view: View?) {
        super.setUpView(view)
        setToolbar(toolbar)
        setUpRecyclerView()
        movieItems = mutableListOf()
        setAdapter(movieItems!!)

        presenter.getMovies()
    }

    private fun setUpRecyclerView() {
        movies_recycler.setHasFixedSize(true)
        movies_recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun setAdapter(items: List<Movie>) {
        moviesAdapter = MoviesAdapter()
        moviesAdapter?.append(items)
        movies_recycler.adapter = moviesAdapter
        if (movieSpacesDecoration == null) {
            movieSpacesDecoration = MovieSpacesDecoration()
            movies_recycler.addItemDecoration(movieSpacesDecoration)
        }
        if (logoDecoration == null) {
            logoDecoration = LogoDecoration(context!!)
            movies_recycler.addItemDecoration(logoDecoration)
        }
    }

    override fun addItems(items: List<Movie>) {
        movieItems?.addAll(items)
        moviesAdapter?.append(items)
    }

    override fun onMoviesError() {
        Toast.makeText(activity, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
    }
}