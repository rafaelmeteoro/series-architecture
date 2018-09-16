package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.PresenterIActivity
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.adapters.HorMoviesFragmentPagerAdapter
import com.example.rafaelfeliciano.seriesarchitecture.util.ViewHelper
import kotlinx.android.synthetic.main.activity_hormovies.*
import javax.inject.Inject

class HorMoviesActivity : PresenterIActivity<HorMoviesContract.Presenter>(), HorMoviesContract.View {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, HorMoviesActivity::class.java)
    }

    @Inject
    internal lateinit var presenter: HorMoviesContract.Presenter

    private lateinit var mCards: MutableList<Movie>
    private lateinit var moviesFragmentAdapter: HorMoviesFragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hormovies)
    }

    override fun getPresenter(): HorMoviesContract.Presenter {
        return presenter
    }

    override fun setUpView() {
        super.setUpView()
        setToolbar(toolbar)
        toolbar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                toolbar.viewTreeObserver.removeOnGlobalLayoutListener(this)
                setUpPagerView()
            }
        })

        moviesFragmentAdapter = HorMoviesFragmentPagerAdapter(supportFragmentManager, ViewHelper.dpToPx(2f))

        viewPager.adapter = moviesFragmentAdapter

        presenter.getMovies()
    }

    private fun setUpPagerView() {

    }

    override fun setItems(items: List<Movie>) {
        mCards = ArrayList()
        mCards.addAll(items)
        moviesFragmentAdapter.setItems(mCards)
    }

    override fun setPageCount(count: Int, selected: Int) {

    }

    override fun onMoviesError() {
        Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
    }
}