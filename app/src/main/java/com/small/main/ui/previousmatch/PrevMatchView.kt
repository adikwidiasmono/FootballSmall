package com.small.main.ui.previousmatch

import com.small.main.data.remote.response.MatchListResponse

interface PrevMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(matchListResponse: MatchListResponse)
    fun showError()

}