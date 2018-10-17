package com.small.main.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.small.main.data.local.dao.MatchDao
import com.small.main.data.local.entity.MatchEntity

@Database(entities = [(MatchEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}