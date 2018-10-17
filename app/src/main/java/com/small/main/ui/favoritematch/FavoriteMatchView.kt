package com.small.main.ui.favoritematch

import com.small.main.data.remote.response.MatchListResponse

interface FavoriteMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(matchListResponse: MatchListResponse)
    fun showError()

}