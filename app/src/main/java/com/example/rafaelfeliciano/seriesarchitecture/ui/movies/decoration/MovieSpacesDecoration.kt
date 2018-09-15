package com.example.rafaelfeliciano.seriesarchitecture.ui.movies.decoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.adapters.MoviesAdapter

class MovieSpacesDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter as? MoviesAdapter ?: return

        if (position >= 0) {

            val margin = view.resources.getDimension(R.dimen.hpad).toInt()
            outRect.top = margin / 2
            outRect.bottom = margin / 2

            if (position == 0) {
                outRect.top = margin
            }
            if (position == adapter.itemCount - 1) {
                outRect.bottom = margin
            }
        }
    }
}