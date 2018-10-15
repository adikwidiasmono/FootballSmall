package com.small.main.data.service

import com.small.main.data.response.EventListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("eventspastleague.php")
    fun loadLastMatches(@Query("id") id: Int): Call<EventListResponse>

    @GET("eventsnextleague.php")
    fun loadNextMatches(@Query("id") id: Int): Call<EventListResponse>

    @GET("eventsday.php?l=English Premier League&s=Soccer")
    fun loadTodayMatch(@Query("id") id: Int, @Query("d") date: String): Call<EventListResponse>
}