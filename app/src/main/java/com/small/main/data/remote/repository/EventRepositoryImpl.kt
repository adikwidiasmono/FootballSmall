package com.small.main.data.remote.repository

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.PlayerListResponse
import com.small.main.data.remote.response.TeamListResponse
import com.small.main.data.remote.service.ApiService
import retrofit2.Call

class EventRepositoryImpl(private val apiService: ApiService) : EventRepository {
    override fun loadAllSoccerLeague(): Call<LeagueListResponse> = apiService.loadAllSoccerLeague()

    override fun loadLastMatchesByTeamId(teamId: Int): Call<MatchListResponse> = apiService.loadLastMatchesByTeamId(teamId)

    override fun loadNextMatchesByTeamId(teamId: Int): Call<MatchListResponse> = apiService.loadNextMatchesByTeamId(teamId)

    override fun loadMatchesByTeamName(teamName: String): Call<MatchListResponse> = apiService.loadMatchesByTeamName(teamName)

    override fun loadTeamsByLeagueId(leagueId: Int): Call<TeamListResponse> = apiService.loadTeamsByLeagueId(leagueId)

    override fun loadPlayersByTeamId(teamId: Int): Call<PlayerListResponse> = apiService.loadPlayersByTeamId(teamId)

    override fun loadTeamsByTeamName(teamName: String): Call<TeamListResponse> = apiService.loadTeamsByTeamName(teamName)

    override fun loadLastMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadLastMatchesByLeagueId(leagueId)

    override fun loadNextMatch(leagueId: Int): Call<MatchListResponse> = apiService.loadNextMatchesByLeagueId(leagueId)

    override fun loadTodayMatch(leagueId: Int, date: String): Call<MatchListResponse> = apiService.loadTodayMatchByLeagueId(leagueId, date)

    override fun lookupTeam(teamId: Int): Call<TeamListResponse> = apiService.lookupTeam(teamId)

}