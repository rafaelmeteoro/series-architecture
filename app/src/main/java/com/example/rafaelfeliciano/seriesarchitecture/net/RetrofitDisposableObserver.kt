package com.example.rafaelfeliciano.seriesarchitecture.net

import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

abstract class RetrofitDisposableObserver<T> : DisposableObserver<T>() {

    override fun onError(e: Throwable) {
        if (e is NullPointerException && e.cause != null) {
            error(e.cause!!)
        } else {
            error(e)
        }
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
        if (throwable is HttpException) {
            val httpException: HttpException = throwable
            val errorBody: ResponseBody = httpException.response().errorBody()!!
            when (httpException.code()) {
                400 -> onBadRequest(errorBody.string())
                401 -> onUnauthorized()
                else -> onUnexpectedError()
            }
        } else if (throwable is IOException) {
            onNetworkError()
        } else {
            onUnexpectedError()
        }
    }

    fun onUnauthorized() {}
    fun onNetworkError() {}
    fun onBadRequest(value: String) {}
    fun onUnexpectedError() {}

    override fun onComplete() {}
}