package com.small.main.data.remote.repository

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.PlayerListResponse
import com.small.main.data.remote.response.TeamListResponse
import retrofit2.Call

interface EventRepository {
    fun loadLastMatch(leagueId: Int): Call<MatchListResponse>

    fun loadNextMatch(leagueId: Int): Call<MatchListResponse>

    fun loadTodayMatch(leagueId: Int, date: String): Call<MatchListResponse>

    fun lookupTeam(teamId: Int): Call<TeamListResponse>

    fun loadAllSoccerLeague(): Call<LeagueListResponse>

    fun loadLastMatchesByTeamId(teamId: Int): Call<MatchListResponse>

    fun loadNextMatchesByTeamId(teamId: Int): Call<MatchListResponse>

    fun loadMatchesByTeamName(teamName: String): Call<MatchListResponse>

    fun loadTeamsByLeagueId(leagueId: Int): Call<TeamListResponse>

    fun loadPlayersByTeamId(teamId: Int): Call<PlayerListResponse>

    fun loadTeamsByTeamName(teamName: String): Call<TeamListResponse>
}