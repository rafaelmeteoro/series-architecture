package com.example.rafaelfeliciano.seriesarchitecture.dao

import com.example.rafaelfeliciano.seriesarchitecture.dao.realm.RealmManager
import io.realm.RealmObject

abstract class BaseDAO<T : RealmObject>(protected var mClass: Class<T>) {

    open fun save(list: List<T>) {
        RealmManager.run {
            realm.executeTransaction { it.copyToRealmOrUpdate(list) }
        }
    }

    open fun save(data: T) {
        RealmManager.run {
            realm.executeTransaction { it.copyToRealmOrUpdate(data) }
        }
    }

    fun findAll(): List<T> =
            RealmManager.run {
                realm.where(mClass)
                        .findAll()
                        .let { realm.copyFromRealm(it) }
            }

    fun find(): T? =
            RealmManager.run {
                realm.where(mClass)
                        .findFirst()
                        ?.let { realm.copyFromRealm(it) }
            }

    fun delete() {
        RealmManager.run {
            realm.executeTransaction { it.delete(mClass) }
        }
    }
}