package com.small.main.data.local.dao

import android.arch.persistence.room.*
import com.small.main.data.local.entity.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM team_entity WHERE id = :id")
    fun get(id: Long): TeamEntity

    @Query("SELECT * FROM team_entity")
    fun getAll(): List<TeamEntity>

    @Query("SELECT * FROM team_entity WHERE id_team = :id")
    fun getByIdTeam(id: Int): TeamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teamEntity: TeamEntity): Long

    @Delete
    fun delete(teamEntity: TeamEntity): Int

}