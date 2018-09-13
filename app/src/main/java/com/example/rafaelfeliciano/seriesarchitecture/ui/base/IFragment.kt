package com.example.rafaelfeliciano.seriesarchitecture.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.ButterKnife
import com.example.rafaelfeliciano.seriesarchitecture.R

open class IFragment : Fragment() {

    val supportActivity: IActivity
        get() = activity as IActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState, view)
        setUpView(view)
    }

    protected open fun init(savedInstanceState: Bundle?, view: View?) {
        ButterKnife.bind(this, view!!)
    }

    protected open fun setUpView(view: View?) {

    }

    protected fun setToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back_wh)
        supportActivity.setSupportActionBar(toolbar)
    }

    open fun onBackPressed() = false
}