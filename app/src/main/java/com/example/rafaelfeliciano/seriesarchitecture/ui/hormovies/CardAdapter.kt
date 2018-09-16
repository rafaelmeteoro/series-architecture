package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.support.v7.widget.CardView

interface CardAdapter {

    companion object {
        val MAX_ELEVATION_FACTOR = 4
    }

    fun getBaseElevation(): Float
    fun getCardViewAt(position: Int): CardView?
    fun getCount(): Int
}