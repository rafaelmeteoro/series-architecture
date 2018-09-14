package com.example.rafaelfeliciano.seriesarchitecture.model.parser

import com.example.rafaelfeliciano.seriesarchitecture.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieParser {

    @Expose
    var title: String? = null
    @Expose
    var year: Int? = null
    @Expose
    var ids: IdsParser? = null
    @Expose
    var overview: String? = null
    @Expose
    var trailer: String? = null
    @Expose
    var homepage: String? = null
    @Expose
    var rating: Double? = null
    @Expose
    var votes: Int? = null
    @Expose
    @SerializedName("comment_count")
    var commentCount: Int? = null

    constructor()

    constructor(movie: Movie) {
        movie.let {
            title = it.title
            year = it.year
            ids = if (it.ids != null) IdsParser(it.ids!!) else IdsParser()
            overview = it.overview
            trailer = it.trailer
            homepage = it.homepage
            rating = it.rating
            votes = it.votes
            commentCount = it.commentCount
        }
    }
}