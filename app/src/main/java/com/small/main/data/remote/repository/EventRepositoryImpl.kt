package com.small.main.data.remote.repository

import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.service.ApiService
import retrofit2.Call

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {

    override fun loadLastMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadLastMatches(leagueId)

    override fun loadNextMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadNextMatches(leagueId)

    override fun loadTodayMatch(leagueId: Int, date: String): Call<MatchListResponse> = apiService.loadTodayMatch(leagueId, date)

}