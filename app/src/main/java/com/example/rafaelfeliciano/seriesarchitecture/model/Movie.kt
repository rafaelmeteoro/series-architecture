package com.example.rafaelfeliciano.seriesarchitecture.model

import com.example.rafaelfeliciano.seriesarchitecture.model.parser.MovieParser
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Movie : RealmObject {

    @PrimaryKey
    var title: String? = null
    var year: Int? = null
    var ids: Ids? = null
    var overview: String? = null
    var trailer: String? = null
    var homepage: String? = null
    var rating: Double? = null
    var votes: Int? = null
    var commentCount: Int? = null

    constructor()

    constructor(parser: MovieParser) {
        title = parser.title
        year = parser.year
        ids = if (parser.ids != null) Ids(parser.ids!!) else Ids()
        overview = parser.overview
        trailer = parser.trailer
        homepage = parser.homepage
        rating = parser.rating
        votes = parser.votes
        commentCount = parser.commentCount
    }
}