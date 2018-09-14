package com.example.rafaelfeliciano.seriesarchitecture.ui.splash

import com.example.rafaelfeliciano.seriesarchitecture.interactor.MovieModel
import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
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

    override fun getMovies(): Observable<List<Movie>> {
        return execute(mMovieModel.get().getMovie(), MovieObserver(), 0)
    }

    private inner class MovieObserver : RetrofitDisposableObserver<List<Movie>>() {
        override fun onNext(t: List<Movie>) {
            Timber.d("Certo")
        }
    }
}