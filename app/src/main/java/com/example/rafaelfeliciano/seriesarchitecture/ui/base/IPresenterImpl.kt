package com.example.rafaelfeliciano.seriesarchitecture.ui.base

abstract class IPresenterImpl<View> : IPresenter<View> {

    private var mView: View? = null

    fun getView(): View? = mView

    override fun attach(view: View) {
        mView = view
    }

    override fun detach() {
        mView = null
    }
}