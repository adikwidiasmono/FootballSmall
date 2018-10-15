package com.small.main.data.repository

import com.small.main.data.response.EventListResponse
import retrofit2.Call

interface EventRepository {
    fun loadLastMatch(leagueId: Int): Call<EventListResponse>

    fun loadNextMatch(leagueId: Int): Call<EventListResponse>

    fun loadTodayMatch(leagueId: Int, date: String): Call<EventListResponse>
}