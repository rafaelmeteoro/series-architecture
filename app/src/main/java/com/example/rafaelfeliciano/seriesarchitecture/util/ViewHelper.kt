package com.example.rafaelfeliciano.seriesarchitecture.util

import android.content.res.Resources

class ViewHelper {

    companion object {
        fun dpToPx(dp: Float): Float = dp * Resources.getSystem().displayMetrics.density
    }
}