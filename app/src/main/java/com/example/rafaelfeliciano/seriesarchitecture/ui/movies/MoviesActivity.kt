package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.InjectableIActivity

class MoviesActivity : InjectableIActivity() {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MoviesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = window.decorView
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, MoviesFragment.newInstance())
                    .commit()
        }
    }
}