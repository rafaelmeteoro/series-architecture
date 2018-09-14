package com.example.rafaelfeliciano.seriesarchitecture.model.parser

import com.example.rafaelfeliciano.seriesarchitecture.model.Ids
import com.google.gson.annotations.Expose

class IdsParser {

    @Expose
    var trakt: Int? = null
    @Expose
    var slug: String? = null
    @Expose
    var tvdb: Int? = null
    @Expose
    var imdb: String? = null
    @Expose
    var tmdb: Int? = null
    @Expose
    var tvrage: Int? = null

    constructor()

    constructor(ids: Ids) {
        ids.let {
            trakt = it.trakt
            slug = it.slug
            tvdb = it.tvdb
            imdb = it.imdb
            tmdb = it.tmdb
            tvrage = it.tvrage
        }
    }
}