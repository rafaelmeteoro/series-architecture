package com.example.rafaelfeliciano.seriesarchitecture.model

import android.os.Parcel
import android.os.Parcelable
import com.example.rafaelfeliciano.seriesarchitecture.model.realm.Json

open class State : Parcelable, Json.Persistable {

    var isUpdated: Boolean = true
    var isFromJob: Boolean = false

    constructor()

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (isUpdated) 1 else 0)
    }

    protected constructor(`in`: Parcel) {
        this.isUpdated = `in`.readByte().toInt() == 1
    }

    companion object {
        val CREATOR: Parcelable.Creator<State> = object : Parcelable.Creator<State> {
            override fun createFromParcel(source: Parcel): State {
                return State(source)
            }

            override fun newArray(size: Int): Array<State?> {
                return arrayOfNulls(size)
            }
        }
    }
}