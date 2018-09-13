package com.example.rafaelfeliciano.seriesarchitecture.ui.base

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.ButterKnife
import com.example.rafaelfeliciano.seriesarchitecture.R

abstract class IActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configure()
    }

    private fun configure() {

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init(savedInstanceState)
        setUpView()
    }

    protected open fun init(savedInstanceState: Bundle?) {
        ButterKnife.bind(this)
    }

    protected open fun setUpView() {

    }

    private val defaultToolbarMargin: Int
        get() = resources?.getDimension(R.dimen.toolbar_navigation_margin)?.toInt() ?: 0

    @JvmOverloads
    protected open fun setToolbar(toolbar: Toolbar,
                                  @DrawableRes drawable: Int = R.drawable.ic_back_wh,
                                  contentInsetsRelative: Int = defaultToolbarMargin) {
        toolbar.setNavigationIcon(drawable)
        toolbar.setContentInsetsRelative(contentInsetsRelative, contentInsetsRelative)
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}