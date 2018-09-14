package com.example.rafaelfeliciano.seriesarchitecture.model

import com.example.rafaelfeliciano.seriesarchitecture.model.parser.IdsParser
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ids : RealmObject {

    @PrimaryKey
    var trakt: Int? = null
    var slug: String? = null
    var tvdb: Int? = null
    var imdb: String? = null
    var tmdb: Int? = null
    var tvrage: Int? = null

    constructor()

    constructor(parser: IdsParser) {
        trakt = parser.trakt
        slug = parser.slug
        tvdb = parser.tvdb
        imdb = parser.imdb
        tmdb = parser.tmdb
        tvrage = parser.tvrage
    }
}