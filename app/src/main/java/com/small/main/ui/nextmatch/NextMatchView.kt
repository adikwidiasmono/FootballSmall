package com.small.main.ui.nextmatch

import com.small.main.data.response.EventListResponse

interface NextMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(eventListResponse: EventListResponse)
    fun showError()

}