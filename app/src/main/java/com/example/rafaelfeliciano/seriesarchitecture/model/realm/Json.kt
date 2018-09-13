package com.example.rafaelfeliciano.seriesarchitecture.model.realm

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Json : RealmObject {

    @PrimaryKey
    private var type: String = ""

    var json: String? = null

    constructor() : super()

    constructor(type: String, json: String) : super() {
        this.type = type
        this.json = json
    }

    inline fun <reified T : Persistable> get(gson: Gson): T? =
            try {
                gson.fromJson(json, T::class.java)
            } catch (error: JsonSyntaxException) {
                null
            }

    interface Persistable {
        fun getType(): String = this::class.java.name
    }

    companion object {
        inline fun <reified T> getType(): String = T::class.java.name
    }
}