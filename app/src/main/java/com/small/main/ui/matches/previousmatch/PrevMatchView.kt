package com.small.main.ui.matches.previousmatch

import com.small.main.data.remote.response.MatchByLeagueListResponse

interface PrevMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(matchByLeagueListResponse: MatchByLeagueListResponse)
    fun showError()

}