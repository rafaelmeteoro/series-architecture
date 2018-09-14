package com.example.rafaelfeliciano.seriesarchitecture.ui.movies

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IModelPresenterImpl
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MoviesPresenter @Inject
constructor(
        private val movieModel: MovieModel
) : IModelPresenterImpl<MoviesContract.View>(), MoviesContract.Presenter {

    override fun getMovies(): Flowable<Catalog> {
        return execute(
                movieModel.getCatalog().toFlowable(BackpressureStrategy.BUFFER),
                MovieObserver()
        )
    }

    private abstract inner class MovieSubscriber<T> : DisposableSubscriber<T>() {
        var view: MoviesContract.View? = getView()

        override fun onComplete() {}
    }

    private inner class MovieObserver : MovieSubscriber<Catalog>() {
        override fun onNext(catalog: Catalog) {
            view?.addItems(catalog.movies)
        }

        override fun onError(t: Throwable?) {
            view?.onMoviesError()
        }
    }
}