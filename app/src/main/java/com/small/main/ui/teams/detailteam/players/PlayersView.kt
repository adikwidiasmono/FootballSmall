package com.small.main.ui.teams.detailteam.players

import com.small.main.data.remote.response.PlayerListResponse

interface PlayersView {

    fun showLoading()
    fun hideLoading()
    fun showPlayerList(playerListResponse: PlayerListResponse)
    fun onErrorPlayerList()

}