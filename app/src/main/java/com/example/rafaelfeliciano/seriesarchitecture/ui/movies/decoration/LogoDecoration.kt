package com.example.rafaelfeliciano.seriesarchitecture.ui.movies.decoration

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.ui.movies.adapters.MoviesAdapter

class LogoDecoration(context: Context) : RecyclerView.ItemDecoration() {

    @SuppressLint("InflateParams")
    private val mLogoView: View = LayoutInflater.from(context)
            .inflate(R.layout.layout_logo, null)
    private val mLogoSize: Int = context.resources.getDimension(R.dimen.logo_size).toInt()
    private val mLogoMargin: Int = context.resources.getDimension(R.dimen.logo_margin).toInt()
    private val mStartMargin: Int = context.resources.getDimension(R.dimen.hpad).toInt()
    private val mRect: Rect = Rect()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val adapter = parent.adapter as? MoviesAdapter ?: return
        val childCount = parent.childCount
        var previousWasDraw = false

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position >= 0 && position < adapter.itemCount) {
                val item = position % 2 == 0
                if (item) {
                    if (position == adapter.itemCount - 1) {
                        drawLogo(c, child, !previousWasDraw)
                    } else if (i == childCount - 1) {
                        drawLogo(c, child, !previousWasDraw)
                    }
                    previousWasDraw = true
                } else if (previousWasDraw) {
                    drawLogo(c, parent.getChildAt(i - 1), false)
                    previousWasDraw = false
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val margin = mLogoSize + mStartMargin + mLogoMargin
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter as? MoviesAdapter ?: return
        if (position >= 0 && position < adapter.itemCount) {
            outRect.left = margin
            outRect.right = mLogoMargin
        }
    }

    private fun drawLogo(c: Canvas, child: View, isFirstMessage: Boolean) {
        child.getLocalVisibleRect(mRect)
        var top = child.bottom - (child.height - mRect.bottom) - mLogoSize
        if (top < child.top && isFirstMessage) {
            top = child.top
        }
        val widthSpec = View.MeasureSpec.makeMeasureSpec(mLogoSize, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(mLogoSize, View.MeasureSpec.EXACTLY)
        mLogoView.measure(widthSpec, heightSpec)
        // Lay the view out with the know dimensions
        mLogoView.layout(mStartMargin, top, mStartMargin + mLogoSize, top + mLogoSize)
        // Translate the canvas so the view is draw at the proper coordinates
        c.save()
        c.translate(mStartMargin.toFloat(), top.toFloat())
        mLogoView.draw(c)
        c.restore()
    }
}