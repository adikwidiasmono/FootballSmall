package com.small.main.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.small.main.data.local.dao.MatchDao
import com.small.main.data.local.dao.TeamDao
import com.small.main.data.local.entity.MatchEntity
import com.small.main.data.local.entity.TeamEntity
import com.small.main.util.RoomDBConverter

@TypeConverters(RoomDBConverter::class)
@Database(entities = [(MatchEntity::class), (TeamEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
    abstract fun teamDao(): TeamDao
}