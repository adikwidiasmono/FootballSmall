package com.small.main.ui.matches.nextmatch

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchByLeagueListResponse

interface NextMatchView {

    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchByLeagueListResponse: MatchByLeagueListResponse)
    fun showLeagueList(leagueListResponse: LeagueListResponse)
    fun showErrorMatchList()
    fun onErrorLeagueList()

}