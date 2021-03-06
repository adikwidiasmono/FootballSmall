package com.small.main.ui.favorites.favoritematch

import com.small.main.data.local.entity.MatchEntity

interface FavoriteMatchView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(listMatchEntity: List<MatchEntity>)
    fun showError()

}