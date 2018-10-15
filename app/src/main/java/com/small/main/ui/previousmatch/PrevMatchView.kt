package com.small.main.ui.previousmatch

import com.small.main.data.response.EventListResponse

interface PrevMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(eventListResponse: EventListResponse)
    fun showError()

}