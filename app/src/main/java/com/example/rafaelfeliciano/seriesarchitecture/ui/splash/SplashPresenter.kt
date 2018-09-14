package com.example.rafaelfeliciano.seriesarchitecture.ui.splash

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.net.RetrofitDisposableObserver
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IModelPresenterImpl
import dagger.Lazy
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class SplashPresenter @Inject
constructor(
        private val mMovieModel: Lazy<MovieModel>
) : IModelPresenterImpl<SplashContract.View>(), SplashContract.Presenter {

    override fun getCatalog(): Observable<Catalog> {
        return execute(mMovieModel.get().getCatalog(), CatalogObserver(), 0)
    }

    private inner class CatalogObserver : RetrofitDisposableObserver<Catalog>() {
        override fun onNext(catalog: Catalog) {
            Timber.d("Chegou")
        }
    }
}