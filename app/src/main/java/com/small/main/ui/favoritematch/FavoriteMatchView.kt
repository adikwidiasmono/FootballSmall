package com.small.main.ui.favoritematch

import com.small.main.data.response.EventListResponse

interface FavoriteMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(eventListResponse: EventListResponse)
    fun showError()

}