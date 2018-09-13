package com.example.rafaelfeliciano.seriesarchitecture.ui.base

import android.support.v4.app.Fragment
import com.example.rafaelfeliciano.seriesarchitecture.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class InjectableIActivity : IActivity(), HasSupportFragmentInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}