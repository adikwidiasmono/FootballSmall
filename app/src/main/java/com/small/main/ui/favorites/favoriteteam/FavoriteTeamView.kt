package com.small.main.ui.favorites.favoriteteam

import com.small.main.data.local.entity.TeamEntity

interface FavoriteTeamView {

    fun showLoading()
    fun hideLoading()
    fun showResultList(listTeamEntity: List<TeamEntity>)
    fun showError()

}