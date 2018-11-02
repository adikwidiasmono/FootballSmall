package com.small.main.data.remote.repository

import com.small.main.data.remote.response.*
import com.small.main.data.remote.service.ApiService
import retrofit2.Call

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {
    override fun loadAllSoccerLeague(): Call<LeagueListResponse> = apiService.loadAllSoccerLeague()

    override fun loadLastMatchesByTeamId(teamId: Int): Call<MatchByTeamListResponse> = apiService.loadLastMatchesByTeamId(teamId)

    override fun loadNextMatchesByTeamId(teamId: Int): Call<MatchByTeamListResponse> = apiService.loadNextMatchesByTeamId(teamId)

    override fun loadMatchesByTeamName(teamName: String): Call<MatchBySearchListResponse> = apiService.loadMatchesByTeamName(teamName)

    override fun loadTeamsByLeagueId(leagueId: Int): Call<TeamListResponse> = apiService.loadTeamsByLeagueId(leagueId)

    override fun loadPlayersByTeamId(teamId: Int): Call<PlayerListResponse> = apiService.loadPlayersByTeamId(teamId)

    override fun loadTeamsByTeamName(teamName: String): Call<TeamListResponse> = apiService.loadTeamsByTeamName(teamName)

    override fun loadLastMatch(leagueId: Int): Call<MatchByLeagueListResponse> = apiService.loadLastMatchesByLeagueId(leagueId)

    override fun loadNextMatch(leagueId: Int): Call<MatchByLeagueListResponse> = apiService.loadNextMatchesByLeagueId(leagueId)

    override fun loadTodayMatch(leagueId: Int, date: String): Call<MatchByLeagueListResponse> = apiService.loadTodayMatchByLeagueId(leagueId, date)

    override fun lookupTeam(teamId: Int): Call<TeamListResponse> = apiService.lookupTeam(teamId)

}