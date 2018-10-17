package com.small.main.data.remote.repository

import com.small.main.data.remote.response.MatchListResponse
import retrofit2.Call

interface EventRepository {
    fun loadLastMatch(leagueId: Int): Call<MatchListResponse>

    fun loadNextMatch(leagueId: Int): Call<MatchListResponse>

    fun loadTodayMatch(leagueId: Int, date: String): Call<MatchListResponse>
}