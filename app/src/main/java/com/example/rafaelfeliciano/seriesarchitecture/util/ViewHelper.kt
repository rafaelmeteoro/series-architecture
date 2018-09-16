package com.example.rafaelfeliciano.seriesarchitecture.util

import android.content.res.Resources

class ViewHelper {

    companion object {
        fun dpToPx(dp: Int): Int = (dp * Resources.getSystem().displayMetrics.density).toInt()
        fun dpToPx(dp: Float): Float = dp * Resources.getSystem().displayMetrics.density
    }
}