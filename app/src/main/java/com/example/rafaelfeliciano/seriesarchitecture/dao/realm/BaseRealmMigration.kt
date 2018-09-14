package com.example.rafaelfeliciano.seriesarchitecture.dao.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration

abstract class BaseRealmMigration(
        val currentVersion: Long,
        private val startVertion: Long = 1
) : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {

    }
}