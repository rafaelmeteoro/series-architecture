package com.example.rafaelfeliciano.seriesarchitecture.model

import android.os.Parcel
import android.os.Parcelable
import com.example.rafaelfeliciano.seriesarchitecture.model.parser.MovieParser
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Movie : RealmObject, Parcelable {

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

    protected constructor(`in`: Parcel) {
        title = `in`.readString()
        year = `in`.readInt()
        ids = `in`.readParcelable(Ids::class.java.classLoader)
        overview = `in`.readString()
        trailer = `in`.readString()
        homepage = `in`.readString()
        rating = `in`.readDouble()
        votes = `in`.readInt()
        commentCount = `in`.readInt()
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeInt(year ?: 0)
        dest.writeParcelable(ids, flags)
        dest.writeString(overview)
        dest.writeString(trailer)
        dest.writeString(homepage)
        dest.writeDouble(rating ?: 0.0)
        dest.writeInt(votes ?: 0)
        dest.writeInt(commentCount ?: 0)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie {
                return Movie(source)
            }

            override fun newArray(size: Int): Array<Movie?> {
                return arrayOfNulls(size)
            }
        }
    }
}