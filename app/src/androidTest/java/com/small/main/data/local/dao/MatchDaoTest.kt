package com.small.main.data.local.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.small.main.data.local.database.AppDatabase
import com.small.main.data.local.entity.MatchEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
open class MatchDaoTest {
    private lateinit var appDatabase: AppDatabase

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun insertMatchSavesData() {
        val matchEntity = generateMatchEntity()
        val id = appDatabase.matchDao().insert(matchEntity)

        val matchEntityRes = appDatabase.matchDao().get(id)
        assert(matchEntityRes != null)
    }

    @Test
    fun getMatchRetrievesData() {
        val listMatchEntity = mutableListOf<MatchEntity>()
        val a = generateMatchEntity()
        val b = generateMatchEntity()
        val c = generateMatchEntity()
        val d = generateMatchEntity()
        val e = generateMatchEntity()
        listMatchEntity.add(a)
        listMatchEntity.add(b)
        listMatchEntity.add(c)
        listMatchEntity.add(d)
        listMatchEntity.add(e)

        listMatchEntity.forEach {
            appDatabase.matchDao().insert(it)
        }

        val listResult = appDatabase.matchDao().getAll()
        assert(listResult == listMatchEntity.sortedWith(compareBy({ it.id }, { it.id })))
    }

    @Test
    fun clearMatchClearsData() {
        val matchEntity = generateMatchEntity()
        val id = appDatabase.matchDao().insert(matchEntity)

        val matchEntityRes = appDatabase.matchDao().get(id)
        appDatabase.matchDao().delete(matchEntityRes)

        assert(appDatabase.matchDao().getAll().isEmpty())
    }

    fun generateMatchEntity(): MatchEntity {
        return MatchEntity(
                genRandomInt(),
                genRandomInt(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomInt(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomInt(),
                genRandomInt(),
                genRandomInt(),
                genRandomInt(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomInt(),
                genRandomInt(),
                Date(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomInt(),
                genRandomInt(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString(),
                genRandomString()
        )
    }

    fun genRandomString(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var random = ""
        for (i in 0..31) {
            random += chars[Math.floor(Math.random() * chars.length).toInt()]
        }
        return random
    }

    fun genRandomInt(): Int {
        return Random().nextInt(9)
    }

}