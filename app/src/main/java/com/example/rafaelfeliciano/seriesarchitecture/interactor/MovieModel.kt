package com.example.rafaelfeliciano.seriesarchitecture.interactor

import com.example.rafaelfeliciano.seriesarchitecture.model.Catalog
import io.reactivex.Observable

interface MovieModel {
    fun getCatalog(): Observable<Catalog>
}