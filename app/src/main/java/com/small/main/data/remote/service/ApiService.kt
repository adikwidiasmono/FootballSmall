package com.small.main.data.remote.service

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.PlayerListResponse
import com.small.main.data.remote.response.TeamListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague.php")
    fun loadLastMatchesByLeagueId(@Query("id") leagueId: Int): Call<MatchListResponse>

    @GET("eventsnextleague.php")
    fun loadNextMatchesByLeagueId(@Query("id") leagueId: Int): Call<MatchListResponse>

    @GET("eventsday.php?l=English Premier League&s=Soccer")
    fun loadTodayMatchByLeagueId(@Query("id") leagueId: Int, @Query("d") date: String): Call<MatchListResponse>

    @GET("lookupteam.php")
    fun lookupTeam(@Query("id") teamId: Int): Call<TeamListResponse>

    @GET("search_all_leagues.php?s=Soccer")
    fun loadAllSoccerLeague(): Call<LeagueListResponse>

    @GET("eventslast.php")
    fun loadLastMatchesByTeamId(@Query("id") teamId: Int): Call<MatchListResponse>

    @GET("eventsnext.php")
    fun loadNextMatchesByTeamId(@Query("id") teamId: Int): Call<MatchListResponse>

    @GET("searchevents.php")
    fun loadMatchesByTeamName(@Query("e") teamName: String): Call<MatchListResponse>

    @GET("lookup_all_teams.php")
    fun loadTeamsByLeagueId(@Query("id") leagueId: Int): Call<TeamListResponse>

    @GET("lookup_all_players.php")
    fun loadPlayersByTeamId(@Query("id") teamId: Int): Call<PlayerListResponse>

    @GET("searchteams.php")
    fun loadTeamsByTeamName(@Query("t") teamName: String): Call<TeamListResponse>

}