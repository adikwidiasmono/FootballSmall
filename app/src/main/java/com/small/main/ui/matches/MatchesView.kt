package com.small.main.ui.matches

import com.small.main.data.remote.response.MatchBySearchListResponse

interface MatchesView {

    fun showLoading()
    fun hideLoading()
    fun showSearchMatchList(matchBySearchListResponse: MatchBySearchListResponse)
    fun onErrorSearchMatchList()

}