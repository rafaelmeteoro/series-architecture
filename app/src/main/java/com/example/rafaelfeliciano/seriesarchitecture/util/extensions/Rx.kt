package com.example.rafaelfeliciano.seriesarchitecture.util.extensions

import io.reactivex.Observable

fun <T> T?.asObservable(): Observable<T> =
        if (this != null) Observable.just(this)
        else Observable.empty()