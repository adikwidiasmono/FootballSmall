package com.small.main.ui.matches.nextmatch

import com.small.main.data.remote.response.MatchByLeagueListResponse

interface NextMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(matchByLeagueListResponse: MatchByLeagueListResponse)
    fun showError()

}