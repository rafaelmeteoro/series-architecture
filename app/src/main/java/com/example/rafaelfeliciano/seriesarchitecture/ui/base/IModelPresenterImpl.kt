package com.example.rafaelfeliciano.seriesarchitecture.ui.base

import dagger.internal.Preconditions
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class IModelPresenterImpl<View> : IPresenterImpl<View>() {

    private var disposables: CompositeDisposable? = null

    override fun detach() {
        super.detach()
        if (disposables != null && !disposables!!.isDisposed) {
            disposables!!.dispose()
        }
    }

    /**
     * Executes the current use case.
     *
     * @param observer The guy who will be listen to the observable build.
     * */
    fun <T> execute(observable: Observable<T>, observer: DisposableObserver<T>, retry: Long = 3): Observable<T> {
        addDisposable(build(observable, retry).subscribeWith(observer))
        return observable
    }

    fun <T> execute(single: Single<T>, observer: DisposableSingleObserver<T>, retry: Long = 3): Single<T> {
        addDisposable(build(single, retry).subscribeWith(observer))
        return single
    }

    fun <T> execute(flowable: Flowable<T>, observer: DisposableSubscriber<T>, retry: Long = 0): Flowable<T> {
        addDisposable(build(flowable, retry).subscribeWith(observer))
        return flowable
    }

    fun execute(completable: Completable, observer: DisposableCompletableObserver, retry: Long = 0): Completable {
        addDisposable(build(completable, retry).subscribeWith(observer))
        return completable
    }

    private fun <T> build(observable: Observable<T>, retry: Long): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(retry) { throwable -> throwable is HttpException && throwable.code() >= 500 || throwable is SocketTimeoutException }
    }

    private fun <T> build(single: Single<T>, retry: Long): Single<T> {
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(retry) { throwable -> throwable is HttpException && throwable.code() >= 500 || throwable is SocketTimeoutException }
    }

    private fun <T> build(flowable: Flowable<T>, retry: Long): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(retry) { throwable -> throwable is HttpException && throwable.code() >= 500 || throwable is SocketTimeoutException }
    }

    private fun build(completable: Completable, retry: Long): Completable {
        return completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(retry) { throwable -> throwable is HttpException && throwable.code() >= 500 || throwable is SocketTimeoutException }
    }

    /**
     * Dispose from current [CompositeDisposable].
     * */
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        if (disposables == null) {
            disposables = CompositeDisposable()
        }
        disposables!!.add(disposable)
    }
}