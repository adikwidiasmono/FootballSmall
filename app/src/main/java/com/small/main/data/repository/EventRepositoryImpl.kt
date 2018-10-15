package com.small.main.data.repository

import com.small.main.data.response.EventListResponse
import com.small.main.data.service.ApiService
import retrofit2.Call

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {

    override fun loadLastMatch(leagueId: Int): Call<EventListResponse> = apiService.loadLastMatches(leagueId)

    override fun loadNextMatch(leagueId: Int): Call<EventListResponse> = apiService.loadNextMatches(leagueId)

    override fun loadTodayMatch(leagueId: Int, date: String): Call<EventListResponse> = apiService.loadTodayMatch(leagueId, date)

}