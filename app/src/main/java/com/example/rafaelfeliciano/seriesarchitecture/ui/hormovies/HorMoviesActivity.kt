package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.PresenterIActivity
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.adapters.HorMoviesFragmentPagerAdapter
import com.example.rafaelfeliciano.seriesarchitecture.util.ViewHelper
import com.example.rafaelfeliciano.seriesarchitecture.util.extensions.diff
import kotlinx.android.synthetic.main.activity_hormovies.*
import javax.inject.Inject

class HorMoviesActivity : PresenterIActivity<HorMoviesContract.Presenter>(), HorMoviesContract.View,
        CardMovieFragment.Listener {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, HorMoviesActivity::class.java)
    }

    @Inject
    internal lateinit var presenter: HorMoviesContract.Presenter

    private lateinit var mCards: MutableList<Movie>
    private lateinit var moviesFragmentAdapter: HorMoviesFragmentPagerAdapter
    private lateinit var pageTransformer: ShadowTransformer

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
        pageTransformer = ShadowTransformer(viewPager)
        viewPager.setPageTransformer(false, pageTransformer)
        viewPager.offscreenPageLimit = 3
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                movie_page_view.setCurrent(position, movie_page_view.current.diff(position) <= 1)
            }
        })

        presenter.getMovies()
    }

    private fun setUpPagerView() {
        val cardHeight = (ViewHelper.dpToPx(154) + resources.getDimension(R.dimen.card_movie_height)).toInt()
        var pageY = viewPager.top + ((viewPager.height - cardHeight) / 2)
        pageY += cardHeight + ViewHelper.dpToPx(50)
        movie_page_view.y = pageY.toFloat()
    }

    override fun setItems(items: List<Movie>) {
        mCards = ArrayList()
        mCards.addAll(items)
        moviesFragmentAdapter.setItems(mCards)
    }

    override fun setPageCount(count: Int, selected: Int) {
        viewPager.setCurrentItem(selected, false)
        movie_page_view.setCount(count)
        movie_page_view.setCurrent(selected, false)
        pageTransformer.enableScaling(true)
    }

    override fun onMoviesError() {
        Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
    }

    override fun onVideoClick(movie: Movie) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setData(Uri.parse(movie.trailer))
        }
        startActivity(intent)
    }

    override fun onHomePageClick(movie: Movie) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setData(Uri.parse(movie.homepage))
        }
        startActivity(intent)
    }
}