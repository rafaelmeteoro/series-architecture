package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import android.database.DataSetObserver
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import java.lang.ref.WeakReference

class ShadowTransformer(private val viewPager: ViewPager)
    : DataSetObserver(), ViewPager.OnPageChangeListener, ViewPager.PageTransformer, ViewPager.OnAdapterChangeListener {

    private var lastOffset = 0f
    private var scalingEnabled = false
    private var cardAdapter = WeakReference(viewPager.adapter as? CardAdapter)
    private var pageScrollState = ViewPager.SCROLL_STATE_IDLE

    private val count: Int
        get() = cardAdapter.get()?.getCount() ?: 0

    init {
        viewPager.addOnPageChangeListener(this)
        viewPager.addOnAdapterChangeListener(this)
    }

    fun enableScaling(enable: Boolean) {
        if (scalingEnabled == enable) return

        scalingEnabled = enable
        refreshScaling()
    }

    private fun refreshScaling() {
        if (count > 0) {
            val currentCard = cardAdapter.get()?.getCardViewAt(viewPager.currentItem)
            val scale = if (scalingEnabled) 1.1f else 1f
            currentCard?.run {
                animate().scaleY(scale)
                animate().scaleX(scale)
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (cardAdapter.get() == null) return

        val realCurrentPosition: Int
        val nextPosition: Int
        val baseElevation = cardAdapter.get()!!.getBaseElevation()
        val realOffset: Float
        val goingLeft = lastOffset > positionOffset

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            nextPosition = position
            realCurrentPosition = position + 1
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }

        // Avoid crash on overscroll
        if (nextPosition > count - 1 || realCurrentPosition > count - 1) return

        val currentCard = cardAdapter.get()?.getCardViewAt(realCurrentPosition)

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (scalingEnabled) {
            currentCard?.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
            currentCard?.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
        }
        currentCard?.cardElevation = baseElevation + (baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1).toFloat() * (1 - realOffset))

        val nextCard = cardAdapter.get()?.getCardViewAt(nextPosition)

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (scalingEnabled) {
            nextCard?.scaleX = (1 + 0.1 * realOffset).toFloat()
            nextCard?.scaleY = (1 + 0.1 * realOffset).toFloat()
        }
        nextCard?.cardElevation = baseElevation + (baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1).toFloat() * realOffset)

        lastOffset = positionOffset
    }

    override fun onPageScrollStateChanged(state: Int) {
        pageScrollState = state
    }

    override fun onPageSelected(position: Int) {
        if (pageScrollState == ViewPager.SCROLL_STATE_IDLE && scalingEnabled) {

        }
    }

    override fun transformPage(page: View, position: Float) {
    }

    override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
        updateAdapter(oldAdapter, newAdapter)
    }

    override fun onChanged() {
        refreshScaling()
    }

    private fun updateAdapter(oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
        oldAdapter?.unregisterDataSetObserver(this)
        newAdapter?.registerDataSetObserver(this)
        val ref = newAdapter.takeIf { it is CardAdapter } as? CardAdapter
        cardAdapter = WeakReference(ref)
        refreshScaling()
    }
}