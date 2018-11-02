package com.small.main.data.remote.repository

import com.small.main.data.remote.response.*
import retrofit2.Call

interface EventRepository {
    fun loadLastMatch(leagueId: Int): Call<MatchByLeagueListResponse>

    fun loadNextMatch(leagueId: Int): Call<MatchByLeagueListResponse>

    fun loadTodayMatch(leagueId: Int, date: String): Call<MatchByLeagueListResponse>

    fun lookupTeam(teamId: Int): Call<TeamListResponse>

    fun loadAllSoccerLeague(): Call<LeagueListResponse>

    fun loadLastMatchesByTeamId(teamId: Int): Call<MatchByTeamListResponse>

    fun loadNextMatchesByTeamId(teamId: Int): Call<MatchByTeamListResponse>

    fun loadMatchesByTeamName(teamName: String): Call<MatchBySearchListResponse>

    fun loadTeamsByLeagueId(leagueId: Int): Call<TeamListResponse>

    fun loadPlayersByTeamId(teamId: Int): Call<PlayerListResponse>

    fun loadTeamsByTeamName(teamName: String): Call<TeamListResponse>
}