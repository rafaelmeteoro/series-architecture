package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.CardView
import android.view.ViewGroup
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.CardAdapter
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.CardMovieFragment
import com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies.HorMovieFragment

class HorMoviesFragmentPagerAdapter(fm: FragmentManager, private val baseElevation: Float) : FragmentStatePagerAdapter(fm), CardAdapter {

    private var fragments = mutableListOf<HorMovieFragment>()

    fun setItems(movies: List<Movie>) {
        fragments = movies.map {
            CardMovieFragment.getInstance(it)
        }.toMutableList()

        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        fragments[position] = fragment as CardMovieFragment
        return fragment
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getBaseElevation(): Float = baseElevation

    override fun getCardViewAt(position: Int): CardView? = fragments[position].getCardView()

    override fun getItemPosition(`object`: Any): Int {
        return fragments.indexOfFirst { it.getItem() == `object` }
    }
}