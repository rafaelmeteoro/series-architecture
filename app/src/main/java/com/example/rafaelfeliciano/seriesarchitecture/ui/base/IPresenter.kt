package com.example.rafaelfeliciano.seriesarchitecture.ui.base

interface IPresenter<View> {
    fun attach(view: View)
    fun detach()
}