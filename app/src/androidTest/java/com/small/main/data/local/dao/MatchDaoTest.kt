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
import org.mockito.Mockito.mock

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
        val matchEntity = mock(MatchEntity::class.java)
        val id = appDatabase.matchDao().insert(matchEntity)

        val matchEntityRes = appDatabase.matchDao().get(id)
        assert(matchEntityRes.value != null)
    }

    @Test
    fun getMatchRetrievesData() {
        val listMatchEntity = mutableListOf<MatchEntity>()
        val a = mock(MatchEntity::class.java)
        val b = mock(MatchEntity::class.java)
        val c = mock(MatchEntity::class.java)
        val d = mock(MatchEntity::class.java)
        val e = mock(MatchEntity::class.java)
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
        val matchEntity = mock(MatchEntity::class.java)
        val id = appDatabase.matchDao().insert(matchEntity)

        val matchEntityRes = appDatabase.matchDao().get(id)
        appDatabase.matchDao().delete(matchEntityRes.value!!)

        assert(appDatabase.matchDao().getAll().isEmpty())
    }

}