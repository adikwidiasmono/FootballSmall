package com.small.main.data.remote.service

import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.TeamListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague.php")
    fun loadLastMatches(@Query("id") id: Int): Call<MatchListResponse>

    @GET("eventsnextleague.php")
    fun loadNextMatches(@Query("id") id: Int): Call<MatchListResponse>

    @GET("eventsday.php?l=English Premier League&s=Soccer")
    fun loadTodayMatch(@Query("id") id: Int, @Query("d") date: String): Call<MatchListResponse>

    @GET("lookupteam.php")
    fun lookupTeam(@Query("id") id: Int): Call<TeamListResponse>

}