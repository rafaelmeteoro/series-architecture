package com.example.rafaelfeliciano.seriesarchitecture.model

import android.os.Parcel
import android.os.Parcelable
import com.example.rafaelfeliciano.seriesarchitecture.model.parser.IdsParser
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ids : RealmObject, Parcelable {

    @PrimaryKey
    var trakt: Int? = null
    var slug: String? = null
    var tvdb: Int? = null
    var imdb: String? = null
    var tmdb: Int? = null
    var tvrage: Int? = null

    constructor()

    protected constructor(`in`: Parcel) {
        trakt = `in`.readInt()
        slug = `in`.readString()
        tvdb = `in`.readInt()
        imdb = `in`.readString()
        tmdb = `in`.readInt()
        tvrage = `in`.readInt()
    }

    constructor(parser: IdsParser) {
        trakt = parser.trakt
        slug = parser.slug
        tvdb = parser.tvdb
        imdb = parser.imdb
        tmdb = parser.tmdb
        tvrage = parser.tvrage
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(trakt ?: 0)
        dest.writeString(slug)
        dest.writeInt(tvdb ?: 0)
        dest.writeString(imdb)
        dest.writeInt(tmdb ?: 0)
        dest.writeInt(tvrage ?: 0)
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<Ids> = object : Parcelable.Creator<Ids> {
            override fun createFromParcel(source: Parcel): Ids {
                return Ids(source)
            }

            override fun newArray(size: Int): Array<Ids?> {
                return arrayOfNulls(size)
            }
        }
    }
}