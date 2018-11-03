package com.small.main.ui.matches.previousmatch

import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchByLeagueListResponse

interface PrevMatchView {

    fun showLoading()
    fun hideLoading()
    fun showMatchList(matchByLeagueListResponse: MatchByLeagueListResponse)
    fun showLeagueList(leagueListResponse: LeagueListResponse)
    fun showErrorMatchList()
    fun onErrorLeagueList()

}