package com.small.main.ui.teams

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.TeamListResponse

interface TeamsView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(teamListResponse: TeamListResponse)
    fun showLeagueList(leagueListResponse: LeagueListResponse)
    fun onErrorTeamList()
    fun onErrorLeagueList()

}