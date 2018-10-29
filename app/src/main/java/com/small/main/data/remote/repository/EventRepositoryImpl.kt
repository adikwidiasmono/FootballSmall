package com.small.main.data.remote.repository

import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.TeamListResponse
import com.small.main.data.remote.service.ApiService
import retrofit2.Call

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {

    override fun loadLastMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadLastMatchesByLeagueId(leagueId)

    override fun loadNextMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadNextMatchesByLeagueId(leagueId)

    override fun loadTodayMatch(leagueId: Int, date: String): Call<MatchListResponse> = apiService.loadTodayMatchByLeagueId(leagueId, date)

    override fun lookupTeam(teamId: Int): Call<TeamListResponse> = apiService.lookupTeam(teamId)

}