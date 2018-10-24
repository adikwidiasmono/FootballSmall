package com.small.main.ui.nextmatch

import com.small.main.data.remote.response.MatchListResponse

interface NextMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(matchListResponse: MatchListResponse)
    fun showError()

}