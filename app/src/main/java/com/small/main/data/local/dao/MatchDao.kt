package com.small.main.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.small.main.data.local.entity.MatchEntity

@Dao
interface MatchDao {

    @Query("SELECT * FROM match_entity WHERE id = :id ")
    fun get(id: Long): LiveData<MatchEntity>

    @Query("SELECT * FROM match_entity")
    fun getAll(): List<MatchEntity>

    @Query("SELECT * FROM match_entity WHERE idEvent = :idEvent ")
    fun getByIdEvent(idEvent: Int): LiveData<MatchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(matchEntity: MatchEntity): Long

    @Delete
    fun delete(matchEntity: MatchEntity): Int

}