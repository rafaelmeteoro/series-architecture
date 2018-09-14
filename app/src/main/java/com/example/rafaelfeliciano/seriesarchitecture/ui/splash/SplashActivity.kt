package com.example.rafaelfeliciano.seriesarchitecture.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.rafaelfeliciano.seriesarchitecture.R
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.PresenterIActivity
import javax.inject.Inject

class SplashActivity : PresenterIActivity<SplashContract.Presenter>(), SplashContract.View {

    @Inject
    lateinit var mPresenter: SplashContract.Presenter

    companion object {
        fun getIntent(context: Context) = Intent(context, SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun getPresenter(): SplashContract.Presenter {
        return mPresenter
    }

    override fun setUpView() {
        super.setUpView()
        mPresenter.getMovies()
    }
}