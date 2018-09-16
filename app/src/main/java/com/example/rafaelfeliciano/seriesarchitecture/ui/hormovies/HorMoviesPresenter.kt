package com.example.rafaelfeliciano.seriesarchitecture.ui.hormovies

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import com.example.rafaelfeliciano.seriesarchitecture.ui.base.IModelPresenterImpl
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class HorMoviesPresenter @Inject
constructor(
        private val movieModel: MovieModel
) : IModelPresenterImpl<HorMoviesContract.View>(), HorMoviesContract.Presenter {

    override fun getMovies(): Flowable<Catalog> {
        return execute(
                movieModel.getCatalog().toFlowable(BackpressureStrategy.BUFFER),
                HorMovieObserver()
        )
    }

    private abstract inner class HorMovieSubscriber<T> : DisposableSubscriber<T>() {
        var view: HorMoviesContract.View? = getView()

        override fun onComplete() {}
    }

    private inner class HorMovieObserver : HorMovieSubscriber<Catalog>() {
        override fun onNext(catalog: Catalog) {
            view?.setItems(catalog.movies)
            view?.setPageCount(catalog.movies.size, 0)
        }

        override fun onError(t: Throwable?) {
            view?.onMoviesError()
        }
    }
}